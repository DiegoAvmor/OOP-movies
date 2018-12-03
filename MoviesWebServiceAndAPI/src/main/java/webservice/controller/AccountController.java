package webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.model.Account;
import webservice.service.AccountService;

@Component
public class AccountController {

    @Autowired
    private AccountService accountService;

    public void createAccount(Account account) {
        accountService.createAccount(account);
    }

    public void updateAccount(Account account)
    {
        accountService.updateAccount(account);
    }

    public boolean existsById(Account account) {
        return accountService.existsById(account);
    }
}
