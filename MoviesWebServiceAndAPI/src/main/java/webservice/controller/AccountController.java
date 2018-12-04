package webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webservice.model.Account;
import webservice.service.AccountService;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public boolean existsById(Account account) {
        return accountService.existsById(account);
    }

    public  Account getAccount(String id)
    {
        return accountService.getAccount(id);
    }

    public Account editAccountDescription(Account account)
    {
        return accountService.editAccountDescription(account);
    }

    public  void deleteAccount(String id){accountService.deleteAccount(id);}
}
