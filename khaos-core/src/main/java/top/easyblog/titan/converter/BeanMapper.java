package top.easyblog.titan.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.easyblog.titan.request.account.CreateAccountRequest;
import top.easyblog.titan.request.account.UpdateAccountRequest;
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

    @Mapping(target = "identifier",source = "request.email")
    @Mapping(target = "credential",source = "request.password")
    @Mapping(target = "userId",source = "userId")
    CreateAccountRequest buildCreateAccountRequest(CreateUserAccountRequest request,Long userId);


    UpdateUserRequest buildUserUpdateRequest(UpdateUserAccountRequest request);

    @Mapping(target = "identifier",source = "email")
    @Mapping(target = "credential",source = "password")
    UpdateAccountRequest buildAccountUpdateRequest(UpdateUserAccountRequest request);
}
