package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.RequestParamAlias;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.request.area.CreatePhoneAreaCodeRequest;
import top.easyblog.titan.request.area.QueryPhoneAreaCodeListRequest;
import top.easyblog.titan.request.area.UpdatePhoneAreaCodeRequest;
import top.easyblog.titan.service.PhoneAreaService;

/**
 * @author: frank.huang
 * @date: 2023-03-12 17:51
 */
@RequestMapping("/v1/phone-area")
@RestController
public class PhoneAreaCodeController {

    @Autowired
    private PhoneAreaService phoneAreaService;

    @ResponseWrapper
    @PostMapping("")
    public void create(@RequestBody CreatePhoneAreaCodeRequest request) {
        phoneAreaService.create(request);
    }

    @ResponseWrapper
    @PutMapping("/{phoneAreaCodeId}")
    public void update(@PathVariable Long phoneAreaCodeId, @RequestBody UpdatePhoneAreaCodeRequest request) {
        phoneAreaService.update(phoneAreaCodeId, request);
    }

    @ResponseWrapper
    @GetMapping("/tree")
    public Object queryPhoneAreaCodeMap(@RequestParamAlias QueryPhoneAreaCodeListRequest request) {
        return phoneAreaService.queryPhoneAreaCodeMap(request);
    }

}
