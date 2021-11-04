package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WithdrawalTransaction")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WithdrawalTransaction extends Transaction{

    public WithdrawalTransaction()
    {
        super();
    }

    public WithdrawalTransaction(Double amount)
    {
        super(amount);
    }

    @Override
    public void generateTransaction(Account account) throws InsufficientBalanceException
    {
        account.withdraw(this.getAmount());
    }
}


