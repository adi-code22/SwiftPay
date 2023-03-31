package com.stackroute.com.TransactionService.model;

import com.stackroute.com.TransactionService.constants.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    @Id
    private String accountNumber;
    @Column(name = "user_email_id", nullable = false)
    private String userEmailId;
    @Enumerated(EnumType.STRING)
    @Column(name = "bank_account_type", nullable = false)
    private AccountType accountType;
    @ManyToOne
    private BankModel bankModel;
    @Column
    private long balance;
    @Column
    private String bankBranch;

}
