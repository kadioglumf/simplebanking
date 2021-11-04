package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kadioglumf
 */
@Service("accountService")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService
{
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;

    @Override
    public Account createAccount(Account account)
    {
        return accountRepository.save(account);
    }

    @Override
    public AccountDto findAccount(String accountNumber)
    {
        Account account = accountRepository.findByAccountNumberEagerly(accountNumber);
        List<TransactionDto> transactionDtos = account.getTransactions()
                .stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        accountDto.setTransactions(transactionDtos);
        return accountDto;
    }

    @Override
    @Transactional
    public TransactionStatus credit(String accountNumber, Transaction transaction) throws InsufficientBalanceException
    {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.post(transaction);
        transaction.setAccount(account);
        accountRepository.save(account);
        return TransactionStatus.builder()
                .status("OK")
                .approvalCode(transaction.getApprovalCode()).build();
    }

    @Override
    @Transactional
    public TransactionStatus debit(String accountNumber, Transaction transaction) throws InsufficientBalanceException
    {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account.getBalance() < transaction.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        account.post(transaction);
        transaction.setAccount(account);
        accountRepository.save(account);
        return TransactionStatus.builder()
                .status("OK")
                .approvalCode(transaction.getApprovalCode()).build();
    }
}
