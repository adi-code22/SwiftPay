package com.stackroute.com.BankService.service;

import com.stackroute.com.BankService.exceptions.CustomException;
import com.stackroute.com.BankService.model.TransactionModel;

import java.io.IOException;

public interface TransactionServiceInterface {

    void addTransactionDetails(TransactionModel transactionModel, String message);

    void updateStatus(int transactionId, String status) throws CustomException;

    boolean verifyAccount(String accountNumber) throws CustomException;

    boolean checkBalance(String accountNumber, float balance) throws CustomException;

    void executeDebit(String message) throws IOException;

    void executeCredit(String message) throws IOException;

}
