package com.stackroute.com.TransactionService.service;

import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.stackroute.com.TransactionService.exceptions.CustomException;
import com.stackroute.com.TransactionService.model.TransactionModel;
import com.stackroute.com.TransactionService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements TransactionServiceInterface{
	@Autowired
	private TransactionRepository repository;

	@Override
	public void addTransaction(TransactionModel transaction) {
//		Optional<TransactionModel> optional = repository.findByAccountNumber(transaction.getAccountNumber());
//		 if(optional.isPresent()){
//			throw new CustomException("Transaction data already Exists");
//			}
		repository.save(transaction);

	}

	@Override
	public List<TransactionModel> getAllTransactions() {
		return repository.findAll();
	}

	@Override
	public List<TransactionModel> getTransactionsByAccountNumber(String accountNumber) throws CustomException {
		Optional<List<TransactionModel>> optional = repository.findAllBySenderAccountNumber(accountNumber);
		List<TransactionModel> model = optional.isEmpty() ? null : optional.get();
		if(model==null)
		{
			throw new CustomException("No transaction related to the Account Number Exists ");
		}
		else {
			return model;
		}
	}

	@Override
	public List<TransactionModel> getTransactionsByReceiverAccountNumber(String accountNumber) throws CustomException {
		Optional<List<TransactionModel>> optional = repository.findAllByReceiverAccountNumber(accountNumber);
		List<TransactionModel> model = optional.isEmpty() ? null : optional.get();
		if(model==null)
		{
			throw new CustomException("No transaction related to the Account Number Exists ");
		}
		else {
			return model;
		}
	}

	@Override
	public boolean checkMT101(String message) throws IOException {
		AbstractMT abstractMT = AbstractMT.parse(message);
		return true;
	}


}
