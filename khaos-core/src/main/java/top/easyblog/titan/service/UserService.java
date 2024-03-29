package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.account.AccountBean;
import top.easyblog.titan.bean.user.UserDetailsBean;
import top.easyblog.titan.converter.BeanMapper;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.feign.client.AccountClient;
import top.easyblog.titan.feign.client.UserClient;
import top.easyblog.titan.request.account.CreateAccountRequest;
import top.easyblog.titan.request.account.QueryAccountRequest;
import top.easyblog.titan.request.account.UpdateAccountRequest;
import top.easyblog.titan.request.user.*;
import top.easyblog.titan.response.KhaosResultCode;
import top.easyblog.titan.response.PageResponse;

import java.util.Objects;
import java.util.Optional;

/**
 * @author: frank.huang
 * @date: 2023-02-25 14:44
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BeanMapper beanMapper;

    public UserDetailsBean details(QueryUserRequest request) {
        return userClient.request(() -> userClient.queryUserDetails(request));
    }


    public PageResponse<UserDetailsBean> queryUserList(QueryUserListRequest request) {
        return userClient.request(() -> userClient.queryUserList(request));
    }

    public UserDetailsBean createUserAccount(CreateUserAccountRequest request) {
        CreateUserRequest createUserRequest = beanMapper.buildUserCreateRequest(request);
        UserDetailsBean userDetailsBean = userClient.request(() -> userClient.createUser(createUserRequest));
        if (Objects.isNull(userDetailsBean)) {
            //创建失败
            throw new BusinessException(KhaosResultCode.CREATE_USER_FAILED);
        }

        CreateAccountRequest createAccountRequest = beanMapper.buildCreateAccountRequest(request, userDetailsBean.getCode());
        accountClient.request(() -> accountClient.createAccount(createAccountRequest));
        return userDetailsBean;
    }


    public void updateUserAccount(String userCode, UpdateUserAccountRequest request) {
        UserDetailsBean userDetailsBean = userClient.request(() -> userClient.queryUserDetails(QueryUserRequest.builder().code(userCode).build()));
        if (Objects.isNull(userDetailsBean)) {
            throw new BusinessException(KhaosResultCode.USER_NOT_FOUND);
        }

        UpdateUserRequest updateUserAccountRequest = beanMapper.buildUserUpdateRequest(request);
        userClient.request(() -> userClient.updateUser(userCode, updateUserAccountRequest));

        Optional.ofNullable(request.getIdentityType()).ifPresent(identityType -> {
            AccountBean accountBean = accountClient.request(() -> accountClient.details(QueryAccountRequest.builder()
                    .userCode(userDetailsBean.getCode()).identityType(identityType).build()));
            if (Objects.isNull(accountBean)) {
                CreateAccountRequest createAccountRequest = beanMapper.buildCreateAccountRequest(CreateUserAccountRequest.builder()
                        .identityType(identityType).email(request.getEmail()).password(request.getPassword()).build(), userDetailsBean.getCode());
                accountClient.request(() -> accountClient.createAccount(createAccountRequest));
                return;
            }

            UpdateAccountRequest updateAccountRequest = beanMapper.buildAccountUpdateRequest(request);
            accountClient.request(() -> accountClient.updateAccount(userDetailsBean.getCode(), identityType, updateAccountRequest));
        });
    }

}
