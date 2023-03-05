package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.account.AccountBean;
import top.easyblog.titan.bean.login.LoginDetailsBean;
import top.easyblog.titan.bean.login.SignInLogBean;
import top.easyblog.titan.bean.roles.RolesBean;
import top.easyblog.titan.converter.BeanMapper;
import top.easyblog.titan.enums.IdentifierType;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.feign.client.AccountClient;
import top.easyblog.titan.feign.client.LoginClient;
import top.easyblog.titan.feign.client.SignInLogClient;
import top.easyblog.titan.feign.client.ZeusClient;
import top.easyblog.titan.request.account.QueryAccountRequest;
import top.easyblog.titan.request.account.UpdateAccountRequest;
import top.easyblog.titan.request.login.*;
import top.easyblog.titan.request.user.QueryUserRequest;
import top.easyblog.titan.response.KhaosResultCode;
import top.easyblog.titan.response.PageResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author: frank.huang
 * @date: 2023-03-03 20:21
 */
@Slf4j
@Service
public class LoginService {


    @Autowired
    private LoginClient loginClient;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private ZeusClient zeusClient;

    @Autowired
    private SignInLogClient signInLogClient;

    @Autowired
    private BeanMapper beanMapper;


    public LoginDetailsBean login(AdminLoginRequest request) {
        // 查询登录邮箱账号是否存在
        AccountBean accountBean = Optional.ofNullable(accountClient.request(() -> accountClient.details(QueryAccountRequest.builder()
                .identityType(IdentifierType.E_MAIL.getCode())
                .identifier(request.getEmail())
                .build()))).orElseThrow(() -> new BusinessException(KhaosResultCode.ACCOUNT_NOT_FOUND));

        Optional.ofNullable(zeusClient.request(() -> zeusClient.queryUserDetails(QueryUserRequest.builder()
                .id(accountBean.getUserId()).sections("roles").build()))).map(item -> {
            List<RolesBean> roles = item.getRoles();
            if (CollectionUtils.isEmpty(roles)) {
                return null;
            }
            return roles.stream().filter(role -> {
                // 只有管理员，负责人可以登录
                return StringUtils.equalsIgnoreCase("admin", role.getCode()) ||
                        StringUtils.equalsIgnoreCase("root", role.getCode()) ||
                        StringUtils.equalsIgnoreCase("owner", role.getCode().toLowerCase());
            }).findFirst().orElse(null);
        }).orElseThrow(() -> new BusinessException(KhaosResultCode.NO_ACCESS_PERMISSION));

        LoginRequest loginRequest = beanMapper.buildAdminLoginRequest(request);
        loginRequest.setExtra(beanMapper.buildAdminSignLogReqeust(request, accountBean));
        return loginClient.request(() -> loginClient.login(loginRequest));
    }

    public Boolean logout(String token) {
        return loginClient.request(() -> loginClient.logout(LogoutRequest.builder()
                .token(token).build()));
    }

    public void modifyPassword(AdminPasswordModifyRequest request) {
        AccountBean accountBean = Optional.ofNullable(accountClient.request(() -> accountClient.details(QueryAccountRequest.builder()
                .identityType(IdentifierType.E_MAIL.getCode())
                .identifier(request.getIdentify())
                .build()))).orElseThrow(() -> new BusinessException(KhaosResultCode.ACCOUNT_NOT_FOUND));

        if (!StringUtils.equals(accountBean.getCredential(), request.getPassword())) {
            throw new BusinessException(KhaosResultCode.PASSWORD_VALID_FAILED);
        }

        if (StringUtils.equals(request.getPassword(), request.getConfigPassword())) {
            throw new BusinessException(KhaosResultCode.PASSWORD_NOT_CHANGE);
        }

        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setCredential(request.getConfigPassword());
        accountClient.request(() -> accountClient.updateAccount(accountBean.getId(), updateAccountRequest));
    }


    public PageResponse<SignInLogBean> querySignLogs(QuerySignInLogListRequest request) {
        return signInLogClient.request(() -> signInLogClient.querySignInLogs(request));
    }
}
