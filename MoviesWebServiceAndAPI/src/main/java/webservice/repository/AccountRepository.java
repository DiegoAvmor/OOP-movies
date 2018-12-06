package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webservice.model.Account;

/**
 * Proporciona métodos CRUD mediante la interfaz {@link JpaRepository}
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
