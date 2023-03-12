package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.service.StaticService;

/**
 * @author: frank.huang
 * @date: 2023-02-26 21:01
 */
@RestController
@RequestMapping("/v1/static")
public class StaticController {

    @Autowired
    private StaticService staticService;


    @ResponseWrapper
    @GetMapping("/identity_types")
    public Object queryAllIdentityType() {
        return staticService.queryAllIdentityType();
    }

    @ResponseWrapper
    @GetMapping("/continents")
    public Object queryAllContinent() {
        return staticService.queryAllContinent();
    }
}
