package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.RequestParamAlias;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.bean.header.UserHeaderImgBean;
import top.easyblog.titan.request.header.CreateUserHeaderImgRequest;
import top.easyblog.titan.request.header.QueryUserHeaderImgRequest;
import top.easyblog.titan.service.HeaderImageService;

import javax.validation.Valid;

/**
 * @author: frank.huang
 * @date: 2023-03-11 15:30
 */
@RequestMapping("/v1/header-image")
@RestController
public class HeaderImageController {

    @Autowired
    private HeaderImageService headerImageService;

    @ResponseWrapper
    @PostMapping
    public void create(@RequestBody @Valid CreateUserHeaderImgRequest request) {
        headerImageService.create(request);
    }

    @ResponseWrapper
    @GetMapping
    public UserHeaderImgBean details(@RequestParamAlias QueryUserHeaderImgRequest request) {
        return headerImageService.details(request);
    }

}
