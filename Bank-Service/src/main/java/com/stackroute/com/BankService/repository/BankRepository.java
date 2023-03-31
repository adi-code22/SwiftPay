package com.stackroute.com.BankService.repository;

import com.stackroute.com.BankService.model.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface BankRepository extends JpaRepository<BankModel, Integer> {
    Optional<BankModel> findByBankId(int bankId);

}
