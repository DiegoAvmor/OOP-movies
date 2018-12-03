package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.model.Account;
import webservice.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public void createAccount(Account account) {
		accountRepository.save(account);
	}

	public  void updateAccount(Account account)
	{
		accountRepository.save(account);
	}

	public boolean existsById(Account account) {
		return accountRepository.existsById(account.getUsername());
	}
}
