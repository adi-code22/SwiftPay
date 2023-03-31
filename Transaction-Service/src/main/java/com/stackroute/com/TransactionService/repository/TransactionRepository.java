package com.stackroute.com.TransactionService.repository;

import com.stackroute.com.TransactionService.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<TransactionModel,String>{
	public Optional<List<TransactionModel>> findAllBySenderAccountNumber(String AccountNumber);

	public Optional<List<TransactionModel>> findAllByReceiverAccountNumber(String AccountNumber);

}
