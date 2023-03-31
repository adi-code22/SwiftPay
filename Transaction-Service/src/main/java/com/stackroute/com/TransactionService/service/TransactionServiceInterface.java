package com.stackroute.com.TransactionService.service;

import com.stackroute.com.TransactionService.exceptions.CustomException;
import com.stackroute.com.TransactionService.model.TransactionModel;

import java.io.IOException;
import java.util.List;

public interface TransactionServiceInterface  {

	public void addTransaction(TransactionModel transaction) throws CustomException;

	public List<TransactionModel> getAllTransactions();

	public List<TransactionModel> getTransactionsByAccountNumber(String AccountNumber) throws CustomException;


	public List<TransactionModel> getTransactionsByReceiverAccountNumber(String AccountNumber) throws CustomException;
	public boolean checkMT101(String message) throws IOException;
}
