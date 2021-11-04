package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.transaction.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bank_account")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(of = {"id","accountNumber"})
@ToString(of = {"id","accountNumber"})
@NoArgsConstructor
public class Account implements Serializable
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
    private String id = UUID.randomUUID().toString();

    @Column(name = "owner")
    private String owner;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Double balance = 0.0;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        setBalance(getBalance() - amount);
    }

    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    public void post(Transaction transaction) throws InsufficientBalanceException
    {
        transaction.generateTransaction(this);
        this.transactions.add(transaction);
    }
}
