package com.eteration.simplebanking.model.transaction.billpayment;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author kadioglumf
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("PhoneBillPaymentTransaction")
public class PhoneBillPaymentTransaction extends BillPaymentTransaction
{
    private String phoneNumber;

    public PhoneBillPaymentTransaction()
    {
        super();
    }

    public PhoneBillPaymentTransaction(double amount) {
        super(amount);
    }

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount) {
        super(amount);
        setPayee(payee);
        setPhoneNumber(phoneNumber);
    }

    @Override
    public void generateTransaction(Account account) throws InsufficientBalanceException
    {
        super.generateTransaction(account);
    }
}
