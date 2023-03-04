package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.bean.login.AuthenticationDetailsBean;
import top.easyblog.titan.constant.Constants;
import top.easyblog.titan.request.login.AdminLoginRequest;
import top.easyblog.titan.service.LoginService;

/**
 * @author: frank.huang
 * @date: 2023-02-25 13:52
 */
@RequestMapping("/v1/auth")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ResponseWrapper
    @PostMapping("/login")
    public AuthenticationDetailsBean login(@RequestBody AdminLoginRequest request) {
        return loginService.login(request);
    }

    @ResponseWrapper
    @PostMapping("/logout")
    public Boolean logout(@RequestHeader(Constants.AUTH_TOKEN) String token) {
        return loginService.logout(token);
    }


}
