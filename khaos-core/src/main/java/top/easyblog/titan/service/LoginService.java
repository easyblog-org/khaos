package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.account.AccountBean;
import top.easyblog.titan.bean.login.LoginDetailsBean;
import top.easyblog.titan.converter.BeanMapper;
import top.easyblog.titan.enums.IdentifierType;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.feign.client.AccountClient;
import top.easyblog.titan.feign.client.LoginClient;
import top.easyblog.titan.request.account.QueryAccountRequest;
import top.easyblog.titan.request.login.AdminLoginRequest;
import top.easyblog.titan.request.login.LoginRequest;
import top.easyblog.titan.request.login.LogoutRequest;
import top.easyblog.titan.response.KhaosResultCode;

import java.util.Objects;

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
    private BeanMapper beanMapper;


    public LoginDetailsBean login(AdminLoginRequest request) {
        AccountBean accountBean = accountClient.request(() -> accountClient.details(QueryAccountRequest.builder()
                .identityType(IdentifierType.E_MAIL.getCode())
                .identifier(request.getEmail())
                .build()));
        if (Objects.isNull(accountBean)) {
            throw new BusinessException(KhaosResultCode.ACCOUNT_NOT_FOUND);
        }

        LoginRequest loginRequest = beanMapper.buildAdminLoginRequest(request);
        loginRequest.setExtra(beanMapper.buildAdminSignLogReqeust(request, accountBean));
        return loginClient.request(() -> loginClient.login(loginRequest));
    }

    public Boolean logout(String token) {
        return loginClient.request(() -> loginClient.logout(LogoutRequest.builder()
                .token(token).build()));
    }
}
