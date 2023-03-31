package com.stackroute.com.TransactionService.controller;

import com.stackroute.com.TransactionService.exceptions.CustomException;
import com.stackroute.com.TransactionService.model.AccountModel;
import com.stackroute.com.TransactionService.model.TransactionModel;
import com.stackroute.com.TransactionService.service.QueueServiceInterface;
import com.stackroute.com.TransactionService.service.TransactionServiceInterface;
import com.stackroute.com.TransactionService.interservice.InterService;
import com.stackroute.com.TransactionService.swift.SwiftOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("transaction-service")
public class Controller {

	@Autowired
	private TransactionServiceInterface transactionService;

	@Autowired
	private InterService interService;
	

	@Autowired
	private QueueServiceInterface queueService;

	@Autowired
	private SwiftOperation swiftOperation;

	/*
	Function To test Get ALL Transaction History
	 */
	@GetMapping("/test")
	public ResponseEntity<?> home() {
		ResponseEntity<?> entity = new ResponseEntity<>("Welcome to Transaction Service", HttpStatus.OK);
		return entity;
	}

/*
Function to Add a Transaction to the history*/

	@PostMapping("/transaction/add")
	public ResponseEntity<?> addTransaction(@RequestBody List<TransactionModel> transaction) {
		ResponseEntity<?> entity = null;
		try {
			transactionService.addTransaction(transaction.get(0));
			entity = new ResponseEntity<>("Transaction added", HttpStatus.OK);


		} catch (CustomException e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	/*function to Get all Transactions*/

	@GetMapping("/transaction/history")
	public ResponseEntity<?> getAllTransactions() {
		List<TransactionModel> allTransactions = transactionService.getAllTransactions();
		ResponseEntity<?> entity = new ResponseEntity<>(allTransactions, HttpStatus.OK);
		return entity;
	}

//	@GetMapping("/transaction/history/{accountNumber}")
//	public ResponseEntity<?> getTransactionsByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
//		ResponseEntity<?> entity = null;
//		try {
//			List<TransactionModel> transaction = transactionService.getTransactionsByAccountNumber(accountNumber);
//			entity = new ResponseEntity<>(transaction, HttpStatus.OK);
//		} catch (CustomException e) {
//			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//		}
//
//		return entity;
//	}

	@GetMapping("/transactions/get")
	public ResponseEntity<?> getTransactions(@RequestHeader Map<String, String> headers  ) {
		String token = headers.get("token");
		System.out.println(token);
		AccountModel account = interService.getAccountDetails(token);
		ResponseEntity<?> entity = null;

		try {
			if(account != null){
				System.out.println(account);
				List<TransactionModel> transactionCredit = transactionService.getTransactionsByAccountNumber(account.getAccountNumber());
				List<TransactionModel> transactionDebit = transactionService.getTransactionsByReceiverAccountNumber(account.getAccountNumber());
				List<TransactionModel> transaction = new ArrayList<>();
				transaction.addAll(transactionCredit);
				transaction.addAll(transactionDebit);

				System.out.println("----------"+transactionDebit.size());
				entity = new ResponseEntity<List<TransactionModel>>(transaction, HttpStatus.OK);
			}
			else {
				entity = new ResponseEntity<String>("Invalid token", HttpStatus.BAD_REQUEST);
			}
		}catch (CustomException e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	/*
	 * For transfer
	 */

	@PostMapping("/transfer")
	public ResponseEntity<?> initiateTransfer(@RequestBody TransactionModel transactionModel) {
		ResponseEntity<?> entity = null;
		try {
			boolean checkMessage = transactionService.checkMT101(transactionModel.getMessage());
			if(checkMessage) {
				queueService.saveQueue(transactionModel.getMessage());
				transactionModel.setStatus("ACK");
				transactionService.addTransaction(transactionModel);

				String MT900 = swiftOperation.generateMT900(transactionModel.getMessage());

				System.out.println("------------MT900----------");
				System.out.println(MT900);
				System.out.println("----------------------------");

				transactionModel.setMessage(MT900);
				System.out.println("display "+transactionModel.toString());
				entity = new ResponseEntity<>(transactionModel, HttpStatus.OK);
			}
		}
		catch (CustomException | IOException e) {
			transactionModel.setStatus("NACK");
			entity = new ResponseEntity<>(transactionModel, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
