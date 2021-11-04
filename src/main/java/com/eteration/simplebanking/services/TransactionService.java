package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.transaction.Transaction;

/**
 * @author kadioglumf
 */
public interface TransactionService
{
    Transaction save(Transaction transaction);

}
