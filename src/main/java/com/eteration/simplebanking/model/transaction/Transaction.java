package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"id","version"})
@Data
@ToString(of = {"id", "version"})
public abstract class Transaction implements Serializable
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
	private String id = UUID.randomUUID().toString();

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_type", insertable=false, updatable=false)
    private String transactionType;

    @CreatedDate
    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "approval_code")
    private String approvalCode = UUID.randomUUID().toString();

    @JsonBackReference(value = "account")
    @ManyToOne
    private Account account;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    @Version
    @Column(name = "version")
    private Integer version;

    public Transaction()
    {
    }

    public Transaction(Double amount)
    {
        this.amount = amount;
        this.transactionDate = new Date();
    }

    public abstract void generateTransaction(Account account) throws InsufficientBalanceException;
}
