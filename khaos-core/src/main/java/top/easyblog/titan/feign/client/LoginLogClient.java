package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import top.easyblog.titan.bean.login.LoginLogBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.login.QueryLoginLogListRequest;
import top.easyblog.titan.request.login.QuerySignInLogRequest;
import top.easyblog.titan.response.PageResponse;

/**
 * @author: frank.huang
 * @date: 2023-03-04 19:10
 */
@FeignClient(name = "login-log", url = "${urls.easyblog}", configuration = CommonFeignConfig.class)
public interface LoginLogClient extends Verify {

    /**
     * 查询登录日志详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/sign-log")
    BaseClientResponse<LoginLogBean> querySignInLogDetails(@SpringQueryMap QuerySignInLogRequest request);

    /**
     * 查询登录日志列表
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/sign-log/list")
    BaseClientResponse<PageResponse<LoginLogBean>> querySignInLogs(@SpringQueryMap QueryLoginLogListRequest request);

}
