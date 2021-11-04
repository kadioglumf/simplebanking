package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DepositTransaction")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DepositTransaction extends Transaction {

    public DepositTransaction()
    {
    }

    public DepositTransaction(Double amount)
    {
        super(amount);
    }

    @Override
    public void generateTransaction(Account account)
    {
        account.deposit(this.getAmount());
    }


}
