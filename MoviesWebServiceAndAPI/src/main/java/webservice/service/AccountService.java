package webservice.service;

import org.hibernate.Hibernate;
import org.hibernate.jpa.HibernateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.controller.AccountController;
import webservice.model.Account;
import webservice.repository.AccountRepository;

/**
 * Provee una capa de separación entre el controlador {@link AccountController} y
 * le interfaz {@link AccountRepository}.
 */
@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * Permite crear una cuenta.
	 * @param account Cuenta nueva.
	 */
	public void createAccount(Account account) {
		accountRepository.save(account);
	}

	/**
	 * Actualiza una cuenta existente.
	 *
	 * @param account Valor de la cuenta nueva. Sobreescribe cuenta con id idéntico.
	 * @return Cuenta nueva.
	 */
	public  Account updateAccountPassword(Account account)
	{
		accountRepository.deleteById(account.getUsername());
		accountRepository.save(account);
		return getAccount(account.getUsername());
	}

	/**
	 * Actualiza una cuenta existente.
	 *
	 * @param account Valor de la cuenta nueva. Sobreescribe cuenta con id idéntico.
	 * @return Cuenta nueva.
	 */
	public Account editAccountDescription(Account account)
	{
		Account aux= account;
		accountRepository.deleteById(account.getUsername());
		accountRepository.save(aux);
		return aux;

	}

	/**
	 * Elimina una cuenta según identificador.
	 *
	 * @param id identificador del registro.
	 */
	public void deleteAccount(String id)
	{
		accountRepository.deleteById(id);

	}

	/**
	 * @param id identificador de la cuenta a retornar.
	 * @return cuenta solicitada.
	 */
	public Account getAccount(String id)
	{
		return accountRepository.findById(id).get();
	}

	/**
	 *
	 * @param account Cuenta a verificar existencia.
	 * @return Indica si la cuenta existe (true) o no (false).
	 */
	public boolean exists(Account account) {
		return accountRepository.existsById(account.getUsername());
	}

	/**
	 *
	 * @param username Identificador de la cuenta a verificar si existe.
	 * @return Indica si la cuenta existe (true) o no (false).
	 */
	public boolean existsById(String username) {
		return accountRepository.existsById(username);
	}
}
