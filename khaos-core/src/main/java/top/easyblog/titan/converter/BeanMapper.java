package top.easyblog.titan.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.easyblog.titan.bean.account.AccountBean;
import top.easyblog.titan.request.account.CreateAccountRequest;
import top.easyblog.titan.request.account.UpdateAccountRequest;
import top.easyblog.titan.request.login.AdminLoginRequest;
import top.easyblog.titan.request.login.CreateSignInLogRequest;
import top.easyblog.titan.request.login.LoginRequest;
import top.easyblog.titan.request.user.CreateUserAccountRequest;
import top.easyblog.titan.request.user.CreateUserRequest;
import top.easyblog.titan.request.user.UpdateUserAccountRequest;
import top.easyblog.titan.request.user.UpdateUserRequest;

/**
 * @author: frank.huang
 * @date: 2023-02-25 12:29
 */
@Mapper(componentModel = "spring")
public interface BeanMapper {


    CreateUserRequest buildUserCreateRequest(CreateUserAccountRequest request);

    @Mapping(target = "identifier", source = "request.email")
    @Mapping(target = "credential", source = "request.password")
    @Mapping(target = "userId", source = "userId")
    CreateAccountRequest buildCreateAccountRequest(CreateUserAccountRequest request, Long userId);


    UpdateUserRequest buildUserUpdateRequest(UpdateUserAccountRequest request);

    @Mapping(target = "identifier", source = "email")
    @Mapping(target = "credential", source = "password")
    UpdateAccountRequest buildAccountUpdateRequest(UpdateUserAccountRequest request);

    @Mapping(target = "identifierType", expression = "java(top.easyblog.titan.enums.IdentifierType.E_MAIL.getSubCode())")
    @Mapping(target = "identifier", source = "request.email")
    @Mapping(target = "credential", source = "request.password")
    LoginRequest buildAdminLoginRequest(AdminLoginRequest request);

   
    @Mapping(target = "ip", source = "request.ip")
    @Mapping(target = "device", source = "request.device")
    @Mapping(target = "operationSystem", source = "request.operationSystem")
    @Mapping(target = "location", source = "request.location")
    CreateSignInLogRequest buildAdminSignLogReqeust(AdminLoginRequest request, AccountBean accountBean);
}
