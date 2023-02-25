package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.bean.user.UserDetailsBean;
import top.easyblog.titan.request.user.CreateUserAccountRequest;
import top.easyblog.titan.request.user.QueryUserListRequest;
import top.easyblog.titan.request.user.QueryUserRequest;
import top.easyblog.titan.request.user.UpdateUserAccountRequest;
import top.easyblog.titan.response.PageResponse;
import top.easyblog.titan.service.UserService;

import javax.validation.Valid;

/**
 * @author: frank.huang
 * @date: 2023-02-25 13:48
 */
@RequestMapping("/v1/user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @ResponseWrapper
    @PostMapping("")
    public UserDetailsBean createUserAccount(@RequestBody @Valid CreateUserAccountRequest request) {
        return userService.createUserAccount(request);
    }

    @ResponseWrapper
    @PutMapping("/{code}")
    public void updateUserAccount(@PathVariable("code") String code, @RequestBody @Valid UpdateUserAccountRequest request) {
        userService.updateUserAccount(code, request);
    }


    @ResponseWrapper
    @GetMapping("")
    public UserDetailsBean details(QueryUserRequest request) {
        return userService.details(request);
    }

    @ResponseWrapper
    @GetMapping("/list")
    public PageResponse<UserDetailsBean> queryList(QueryUserListRequest request) {
        return userService.queryUserList(request);
    }



}
