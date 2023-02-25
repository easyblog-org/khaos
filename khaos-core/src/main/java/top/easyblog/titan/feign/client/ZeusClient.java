package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.bean.roles.RolesBean;
import top.easyblog.titan.bean.user.UserDetailsBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Response;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.account.CreateAccountRequest;
import top.easyblog.titan.request.account.UpdateAccountRequest;
import top.easyblog.titan.request.roles.QueryRolesDetailsRequest;
import top.easyblog.titan.request.roles.QueryRolesListRequest;
import top.easyblog.titan.request.user.CreateUserRequest;
import top.easyblog.titan.request.user.QueryUserListRequest;
import top.easyblog.titan.request.user.QueryUserRequest;
import top.easyblog.titan.request.user.UpdateUserRequest;
import top.easyblog.titan.response.PageResponse;

import java.util.List;

/**
 * @author: frank.huang
 * @date: 2023-02-25 14:27
 */
@FeignClient(name = "zeus", url = "${urls.zeus}", configuration = CommonFeignConfig.class)
public interface ZeusClient extends Verify {

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    @PostMapping("/v1/in/user")
    BaseClientResponse<UserDetailsBean> createUser(@RequestBody CreateUserRequest request);

    /**
     * 更新用户
     *
     * @param request
     * @return
     */
    @PutMapping("/v1/in/user/{code}")
    BaseClientResponse<Integer> updateUser(@PathVariable("code") String code, @RequestBody UpdateUserRequest request);

    /**
     * 查询用户详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/user")
    BaseClientResponse<UserDetailsBean> queryUserDetails(@SpringQueryMap QueryUserRequest request);

    /**
     * 查询用户列表
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/user/list")
    BaseClientResponse<PageResponse<UserDetailsBean>> queryUserList(@SpringQueryMap QueryUserListRequest request);

}
