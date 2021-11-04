package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author kadioglumf
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String>
{
    @Query("select bankAccount from Account bankAccount left join fetch bankAccount.transactions transactions where bankAccount.accountNumber =:accountNumber")
    Account findByAccountNumberEagerly(@Param("accountNumber") String accountNumber);

    Account findByAccountNumber(String accountNumber);
}
