package webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
