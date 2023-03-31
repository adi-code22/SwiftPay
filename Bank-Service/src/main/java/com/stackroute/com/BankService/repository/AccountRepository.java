package com.stackroute.com.BankService.repository;

import com.stackroute.com.BankService.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<AccountModel, String> {
    Optional<AccountModel> findByAccountNumber(String accountNumber);

    Optional<AccountModel> findByUserEmailId(String emailId);
}
