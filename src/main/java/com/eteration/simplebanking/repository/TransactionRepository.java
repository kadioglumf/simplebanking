package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kadioglumf
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>
{
}
