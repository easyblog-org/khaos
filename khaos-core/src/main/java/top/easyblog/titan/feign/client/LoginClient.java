package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.easyblog.titan.bean.login.LoginDetailsBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.login.LoginRequest;
import top.easyblog.titan.request.login.LogoutRequest;

/**
 * @author: frank.huang
 * @date: 2023-03-03 20:23
 */
@FeignClient(name = "login", url = "${urls.easyblog}", configuration = CommonFeignConfig.class)
public interface LoginClient extends Verify {

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @PostMapping("/v1/auth/login")
    BaseClientResponse<LoginDetailsBean> login(@RequestBody LoginRequest request);

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/v1/auth/logout")
    BaseClientResponse<Boolean> logout(@RequestBody LogoutRequest request);
}
