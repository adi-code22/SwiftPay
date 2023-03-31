package com.stackroute.com.BankService.service;

import com.stackroute.com.BankService.exceptions.CustomException;
import com.stackroute.com.BankService.model.AccountModel;
import com.stackroute.com.BankService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    private AccountRepository repository;

    @Override
    public void addAccountDetails(AccountModel accountModel) throws CustomException {
        Optional<AccountModel> optional = repository.findById(accountModel.getAccountNumber());
        if(optional.isPresent()) {
            throw new CustomException("Bank account already exists");
        }
        repository.save(accountModel);
    }

    @Override
    public List<AccountModel> viewAllAccounts() {
        return repository.findAll();
    }

    @Override
    public AccountModel getAccount(String accountNumber) throws CustomException{
        Optional<AccountModel> optional = repository.findByAccountNumber(accountNumber);
        AccountModel account = optional.isEmpty() ? null : optional.get();
        if(account == null) {
            throw new CustomException("Account not found");
        }
        else {
            return account;
        }
    }

    @Override
    public void deleteAccount(String accountNumber) throws CustomException {
        Optional<AccountModel> optional = repository.findByAccountNumber(accountNumber);
        AccountModel account = optional.isEmpty() ? null : optional.get();
        if(account == null) {
            throw new CustomException("Account not found");
        }
        else {
            repository.deleteById(accountNumber);
        }
    }

    @Override
    public void updateAccount(String accountNumber, AccountModel account) throws CustomException {
        Optional<AccountModel> optional = repository.findByAccountNumber(accountNumber);
        AccountModel update = optional.isEmpty() ? null : optional.get();
        if(update == null) {
            throw new CustomException("Account not found");
        }
        else {
            update.setUserEmailId(account.getUserEmailId());
            update.setAccountType(account.getAccountType());
            update.setBankModel(account.getBankModel());
            update.setBalance(account.getBalance());
            repository.save(update);
        }
    }

    @Override
    public AccountModel getAccountByUserEmailId(String emailId) throws CustomException {
        Optional<AccountModel> optional = repository.findByUserEmailId(emailId);
        AccountModel account = optional.isEmpty() ? null : optional.get();
        if(account == null) {
            throw new CustomException("Invalid User");
        }
        else {
            return account;
        }
    }
}
