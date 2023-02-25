package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.bean.account.AccountBean;
import top.easyblog.titan.bean.roles.RolesBean;
import top.easyblog.titan.bean.user.UserDetailsBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.account.CreateAccountRequest;
import top.easyblog.titan.request.account.QueryAccountListRequest;
import top.easyblog.titan.request.account.QueryAccountRequest;
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
@FeignClient(name = "account", url = "${urls.zeus}", configuration = CommonFeignConfig.class)
public interface AccountClient extends Verify {

    /**
     * 创建Account
     *
     * @param request
     * @return
     */
    @PostMapping("/v1/in/account")
    BaseClientResponse<Void> createAccount(@RequestBody CreateAccountRequest request);

    /**
     * 更新Account
     *
     * @param request
     * @return
     */
    @PutMapping("/v1/in/account/{account_id}")
    BaseClientResponse<Void> updateAccount(@PathVariable("account_id") Long accountId,
                                           @RequestBody UpdateAccountRequest request);


    /**
     * 更新Account
     *
     * @param request
     * @return
     */
    @PutMapping("/v1/in/account/{user_id}/{identify_type}")
    BaseClientResponse<Void> updateAccount(@PathVariable("user_id") Long userId,
                                           @PathVariable("identify_type") Integer identityType,
                                           @RequestBody UpdateAccountRequest request);


    /**
     * 查询Account详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/account")
    BaseClientResponse<AccountBean> details(@SpringQueryMap QueryAccountRequest request);

    /**
     * 查询Account列表
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/account/list")
    BaseClientResponse<PageResponse<AccountBean>> queryAccountList(@SpringQueryMap QueryAccountListRequest request);
}
