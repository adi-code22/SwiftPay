package com.stackroute.com.BankService.service;

import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.prowidesoftware.swift.model.mt.mt9xx.MT900;
import com.prowidesoftware.swift.model.mt.mt9xx.MT910;
import com.stackroute.com.BankService.exceptions.CustomException;
import com.stackroute.com.BankService.model.AccountModel;
import com.stackroute.com.BankService.model.TransactionModel;
import com.stackroute.com.BankService.repository.AccountRepository;
import com.stackroute.com.BankService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void addTransactionDetails(TransactionModel transactionModel, String MT101) {
        System.out.println("*********Inside transaction service add method************");
        transactionModel.setMessage(MT101);
        transactionRepository.save(transactionModel);
    }

    @Override
    public void updateStatus(int transactionId, String status) throws CustomException {
        Optional<TransactionModel> optional = transactionRepository.findByTransactionId(transactionId);
        TransactionModel model = optional.isEmpty() ? null : optional.get();
        if(model == null) {
            throw new CustomException("No transaction found with id");
        }
        model.setStatus(status);
        transactionRepository.save(model);
        System.out.println("************inside update status in transaction service************");
    }

    @Override
    public boolean verifyAccount(String accountNumber) throws CustomException {
        System.out.println(accountNumber);
        Optional<AccountModel> optional = accountRepository.findByAccountNumber(accountNumber);
        AccountModel model = optional.isEmpty() ? null : optional.get();
        if(model == null) {
            throw new CustomException("Account number not found");
        }
        else {
            return true;
        }
    }

    @Override
    public boolean checkBalance(String accountNumber, float balance) throws CustomException{
        Optional<AccountModel> optional = accountRepository.findByAccountNumber(accountNumber);
        AccountModel model = optional.isEmpty() ? null :optional.get();
        if(model == null) {
            throw new CustomException("Account number does not exist");
        }
        else if(model.getBalance() < balance) {
            throw new CustomException("Balance is low");
        }
        else {
            return true;
        }
    }

    @Override
    public void executeDebit(String message) throws IOException {
        System.out.println(message);
        AbstractMT abstractMT = AbstractMT.parse(message);
        MT900 mt900 = (MT900) abstractMT;
        System.out.println("****AFTER MT900****" + mt900.message());
        String debitAmount = String.valueOf(mt900.getField32A().getAmount()).replace(",", "");
        System.out.println(1 + "-------" + debitAmount);
        String accountNumber = mt900.getField25().getComponent1().replace("A", "");
        System.out.println(2 + "----------" + accountNumber);
        Optional<AccountModel> optional = accountRepository.findByAccountNumber(accountNumber);
        System.out.println(3);
        AccountModel accountModel = optional.isEmpty() ? null : optional.get();
        System.out.println(4 + "------------" + accountModel.toString());
        accountModel.setBalance(accountModel.getBalance() - Long.parseLong(debitAmount));
        System.out.println(5);
        accountRepository.save(accountModel);
        System.out.println("************inside DEBIT in transaction service************");
    }

    @Override
    public void executeCredit(String message) throws IOException {
        System.out.println("INSIDE CREDIT");
        AbstractMT abstractMT = AbstractMT.parse(message);
        System.out.println(1);
        MT910 mt910 = (MT910) abstractMT;
        System.out.println(2);
        String creditAmount = String.valueOf(mt910.getField32A().getAmount()).replace(",", "");
        System.out.println(creditAmount);
        String accountNumber = mt910.getField25().getValue().replace("X", "");
        System.out.println(accountNumber);
        Optional<AccountModel> optional = accountRepository.findByAccountNumber(accountNumber);
        AccountModel accountModel = optional.isEmpty() ? null : optional.get();
        accountModel.setBalance(accountModel.getBalance() + Long.parseLong(creditAmount));
        accountRepository.save(accountModel);
    }


}
