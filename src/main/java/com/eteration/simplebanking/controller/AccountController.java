package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.transaction.DepositTransaction;
import com.eteration.simplebanking.model.transaction.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankAccount/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }


    @GetMapping("/{account-number}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("account-number") String accountNumber) {
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("/credit/{account-number}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable("account-number") String accountNumber, @RequestBody DepositTransaction transaction) throws InsufficientBalanceException
    {
        TransactionStatus transactionStatus = accountService.credit(accountNumber, transaction);
        return ResponseEntity.ok(transactionStatus);
    }

    @PostMapping("/debit/{account-number}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable("account-number") String accountNumber, @RequestBody WithdrawalTransaction transaction) throws InsufficientBalanceException
    {
        TransactionStatus transactionStatus = accountService.debit(accountNumber, transaction);
        return ResponseEntity.ok(transactionStatus);
	}
}