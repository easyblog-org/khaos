package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.bean.area.PhoneAreaCodeBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.area.CreatePhoneAreaCodeRequest;
import top.easyblog.titan.request.area.QueryPhoneAreaCodeListRequest;
import top.easyblog.titan.request.area.UpdatePhoneAreaCodeRequest;
import top.easyblog.titan.response.PageResponse;

import java.util.List;

/**
 * @author: frank.huang
 * @date: 2023-03-12 14:54
 */
@FeignClient(name = "phone-area", url = "${urls.easyblog}", configuration = CommonFeignConfig.class)
public interface PhoneAreaClient extends Verify {

    @PostMapping("/v1/in/area-code")
    BaseClientResponse<Void> create(@RequestBody CreatePhoneAreaCodeRequest request);


    @GetMapping("/v1/in/area-code/list")
    BaseClientResponse<PageResponse<PhoneAreaCodeBean>> list(@SpringQueryMap QueryPhoneAreaCodeListRequest request);


    @PutMapping("/v1/in/area-code/{id}")
    BaseClientResponse<Void> update(@PathVariable("id") Long id,
                                    @RequestBody UpdatePhoneAreaCodeRequest request);

    @DeleteMapping("/v1/in/area-code")
    BaseClientResponse<Void> deleteByIds(@RequestParam("phone_area_code_ids") List<Long> phoneAreaCodeIds,
                                         @RequestParam("password") String password);

}
