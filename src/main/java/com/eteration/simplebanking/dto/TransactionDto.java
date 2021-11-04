package com.eteration.simplebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kadioglumf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto
{
    private Date transactionDate;
    private Double amount;
    private String transactionType;
    private String approvalCode;
}
