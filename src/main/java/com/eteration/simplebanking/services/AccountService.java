package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.Transaction;

// This class is a place holder you can change the complete implementation
public interface AccountService {

    Account createAccount(Account account);

    AccountDto findAccount(String accountNumber);

    TransactionStatus credit(String accountNumber, Transaction transaction) throws InsufficientBalanceException;

    TransactionStatus debit(String accountNumber, Transaction transaction) throws InsufficientBalanceException;
}
