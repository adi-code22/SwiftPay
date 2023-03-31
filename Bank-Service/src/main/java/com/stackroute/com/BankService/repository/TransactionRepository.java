package com.stackroute.com.BankService.repository;

import com.stackroute.com.BankService.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
    Optional<TransactionModel> findByTransactionId(int transactionId);
}
