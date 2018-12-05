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

    public Account updateAccountPassword(Account account)
    {
        return accountService.updateAccountPassword(account);
    }

    public boolean exists(Account account) {
        return accountService.exists(account);
    }

    public boolean existsById(String username) {
        return accountService.existsById(username);
    }

    public Account getAccount(String id)
    {
        return accountService.getAccount(id);
    }

    public Account editAccountDescription(Account account)
    {
        return accountService.editAccountDescription(account);
    }

    public  void deleteAccount(String id){
        accountService.deleteAccount(id);
    }
}
