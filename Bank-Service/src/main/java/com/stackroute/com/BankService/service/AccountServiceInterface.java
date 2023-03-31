package com.stackroute.com.BankService.service;

import com.stackroute.com.BankService.exceptions.CustomException;
import com.stackroute.com.BankService.model.AccountModel;

import java.util.List;

public interface AccountServiceInterface {
    void addAccountDetails(AccountModel accountModel) throws CustomException;

    List<AccountModel> viewAllAccounts();

    AccountModel getAccount(String accountNumber) throws CustomException;

    void deleteAccount(String accountNumber) throws CustomException;

    void updateAccount(String accountNumber, AccountModel account) throws CustomException;

    AccountModel getAccountByUserEmailId(String emailId) throws CustomException;
}
