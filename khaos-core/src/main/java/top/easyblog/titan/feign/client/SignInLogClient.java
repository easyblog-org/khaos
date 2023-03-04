package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import top.easyblog.titan.bean.login.SignInLogBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.request.login.QuerySignInLogListRequest;
import top.easyblog.titan.request.login.QuerySignInLogRequest;
import top.easyblog.titan.response.PageResponse;

/**
 * @author: frank.huang
 * @date: 2023-03-04 19:10
 */
@FeignClient(name = "signin-log", url = "${urls.zeus}", configuration = CommonFeignConfig.class)
public interface SignInLogClient {

    /**
     * 查询登录日志详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/sign-log")
    BaseClientResponse<SignInLogBean> querySignInLogDetails(@SpringQueryMap QuerySignInLogRequest request);

    /**
     * 查询登录日志列表
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/sign-log/list")
    BaseClientResponse<PageResponse<SignInLogBean>> querySignInLogs(@SpringQueryMap QuerySignInLogListRequest request);

}
