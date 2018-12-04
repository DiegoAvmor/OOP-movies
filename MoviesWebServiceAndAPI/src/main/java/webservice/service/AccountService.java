package webservice.service;

import org.hibernate.Hibernate;
import org.hibernate.jpa.HibernateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.model.Account;
import webservice.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public void createAccount(Account account) {
		accountRepository.save(account);
	}

	public  Account updateAccountPassword(Account account)
	{
		accountRepository.deleteById(account.getUsername());
		accountRepository.save(account);
		return getAccount(account.getUsername());
	}
	public Account editAccountDescription(Account account)
	{
		Account aux= account;
		accountRepository.deleteById(account.getUsername());
		accountRepository.save(aux);
		return aux;

	}
	public void deleteAccount(String id)
	{
		accountRepository.deleteById(id);

	}

	public Account getAccount(String id)
	{
		return accountRepository.findById(id).get();
	}
	public boolean existsById(Account account) {
		return accountRepository.existsById(account.getUsername());
	}
}
