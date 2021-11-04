package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.model.transaction.Transaction;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.eteration.simplebanking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kadioglumf
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService
{
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction save(Transaction transaction)
    {
        return transactionRepository.save(transaction);
    }
}
