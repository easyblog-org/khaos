package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.easyblog.titan.bean.header.UserHeaderImgBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.header.CreateUserHeaderImgRequest;
import top.easyblog.titan.request.header.QueryUserHeaderImgRequest;

/**
 * @author: frank.huang
 * @date: 2023-03-11 15:34
 */
@FeignClient(name = "header-image", url = "${urls.zeus}", configuration = CommonFeignConfig.class)
public interface HeaderImageClient extends Verify {

    @PostMapping("/v1/in/header-img")
    BaseClientResponse<Void> create(@RequestBody CreateUserHeaderImgRequest request);


    @GetMapping("/v1/in/header-img")
    BaseClientResponse<UserHeaderImgBean> details(@SpringQueryMap QueryUserHeaderImgRequest request);

}
