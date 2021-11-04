package com.eteration.simplebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kadioglumf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto
{
    private String owner;
    private String accountNumber;
    private Double balance;
    private Date createdDate;
    private List<TransactionDto> transactions = new ArrayList<>();

    public AccountDto(String owner, String accountNumber)
    {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }
}
