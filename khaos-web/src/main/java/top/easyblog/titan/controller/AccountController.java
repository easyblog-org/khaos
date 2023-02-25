package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.RequestParamAlias;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.request.account.CreateAccountRequest;
import top.easyblog.titan.request.account.QueryAccountListRequest;
import top.easyblog.titan.request.account.QueryAccountRequest;
import top.easyblog.titan.request.account.UpdateAccountRequest;
import top.easyblog.titan.service.AccountService;

import javax.validation.Valid;

/**
 * @author: frank.huang
 * @date: 2023-02-25 16:28
 */
@RequestMapping("/v1/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ResponseWrapper
    @PostMapping
    public void create(@RequestBody @Valid CreateAccountRequest request) {
        accountService.createAccount(request);
    }

    @ResponseWrapper
    @GetMapping
    public Object query(@Valid @RequestParamAlias QueryAccountRequest request) {
        return accountService.queryAccountDetails(request);
    }

    @ResponseWrapper
    @PutMapping("/{account_id}")
    public void update(@PathVariable("account_id") Long accountId,
                       @RequestBody @Valid UpdateAccountRequest request) {
        accountService.updateAccount(accountId,request);
    }

    @ResponseWrapper
    @GetMapping("/list")
    public Object queryList(@Valid @RequestParamAlias QueryAccountListRequest request) {
        return accountService.queryAccountList(request);
    }

}
