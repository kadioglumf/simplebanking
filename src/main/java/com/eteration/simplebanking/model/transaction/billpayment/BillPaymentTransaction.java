package com.eteration.simplebanking.model.transaction.billpayment;

import com.eteration.simplebanking.model.transaction.WithdrawalTransaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author kadioglumf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BillPaymentTransaction extends WithdrawalTransaction
{
    public String payee;

    public BillPaymentTransaction()
    {
        super();
    }

    public BillPaymentTransaction(double amount) {
        super(amount);
    }
}
