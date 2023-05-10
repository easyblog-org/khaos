package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.RequestParamAlias;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.bean.login.AuthenticationDetailsBean;
import top.easyblog.titan.bean.login.LoginLogBean;
import top.easyblog.titan.constant.Constants;
import top.easyblog.titan.request.login.AdminLoginRequest;
import top.easyblog.titan.request.login.AdminPasswordModifyRequest;
import top.easyblog.titan.request.login.QueryLoginLogListRequest;
import top.easyblog.titan.response.PageResponse;
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

    @ResponseWrapper
    @GetMapping("/refresh")
    public AuthenticationDetailsBean refresh(@RequestParam("userCode") String userCode,
                                             @RequestParam("accountCode") String accountCode) {
        return loginService.refresh(userCode, accountCode);
    }

    @ResponseWrapper
    @PostMapping("/modify-pwd")
    public void modifyPassword(@RequestBody AdminPasswordModifyRequest request) {
        loginService.modifyPassword(request);
    }


    @ResponseWrapper
    @GetMapping("/logs")
    public PageResponse<LoginLogBean> queryLoginLogs(@RequestParamAlias QueryLoginLogListRequest request) {
        return loginService.queryLoginLogs(request);
    }
}
