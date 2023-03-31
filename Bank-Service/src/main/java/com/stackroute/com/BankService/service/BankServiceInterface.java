package com.stackroute.com.BankService.service;

import com.stackroute.com.BankService.exceptions.CustomException;
import com.stackroute.com.BankService.model.BankModel;

import java.util.List;

public interface BankServiceInterface {
    void addBankDetails(BankModel bank) throws CustomException;

    List<BankModel> getAllBankDetails();

    BankModel getBankById(int bankId) throws CustomException;

    void deleteBankByBankId(int bankId) throws CustomException;

    void updateBankById(int bankId, BankModel bank) throws CustomException;
}
