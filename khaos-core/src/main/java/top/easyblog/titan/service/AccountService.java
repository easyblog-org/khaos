package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.account.AccountBean;
import top.easyblog.titan.feign.client.AccountClient;
import top.easyblog.titan.request.account.CreateAccountRequest;
import top.easyblog.titan.request.account.QueryAccountListRequest;
import top.easyblog.titan.request.account.QueryAccountRequest;
import top.easyblog.titan.request.account.UpdateAccountRequest;
import top.easyblog.titan.response.PageResponse;

/**
 * @author: frank.huang
 * @date: 2023-02-25 16:29
 */
@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountClient accountClient;


    public void createAccount(CreateAccountRequest request) {
        accountClient.request(() -> accountClient.createAccount(request));
    }

    public AccountBean queryAccountDetails(QueryAccountRequest request) {
        return accountClient.request(() -> accountClient.details(request));
    }

    public void updateAccount(String accountCode, UpdateAccountRequest request) {
        accountClient.request(() -> accountClient.updateAccount(accountCode, request));
    }

    public PageResponse<AccountBean> queryAccountList(QueryAccountListRequest request) {
        return accountClient.request(() -> accountClient.queryAccountList(request));
    }
}
