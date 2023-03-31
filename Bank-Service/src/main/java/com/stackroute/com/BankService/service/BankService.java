package com.stackroute.com.BankService.service;

import com.stackroute.com.BankService.exceptions.CustomException;
import com.stackroute.com.BankService.model.BankModel;
import com.stackroute.com.BankService.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService implements BankServiceInterface {
    @Autowired
    private BankRepository repository;


    @Override
    public void addBankDetails(BankModel bank) throws CustomException {
        Optional<BankModel> optional = repository.findById(bank.getBankId());
        if(optional.isPresent()) {
            throw new CustomException("Bank already exists with this bank name");
        }
        repository.save(bank);
    }

    @Override
    public List<BankModel> getAllBankDetails() {
        return repository.findAll();
    }

    @Override
    public BankModel getBankById(int bankId) throws CustomException{
        Optional<BankModel> optional = repository.findByBankId(bankId);
        BankModel model = optional.isEmpty() ? null : optional.get();
        if(model == null) {
            throw new CustomException("Bank not found");
        }
        else {
            return model;
        }
    }

    @Override
    public void deleteBankByBankId(int bankId) throws CustomException {
        Optional<BankModel> optional = repository.findByBankId(bankId);
        BankModel model = optional.isEmpty() ? null : optional.get();
        if(model == null) {
            throw new CustomException("Bank not found");
        }
        else {
            repository.deleteById(bankId);
        }
    }

    @Override
    public void updateBankById(int bankId, BankModel bank) throws CustomException {
        Optional<BankModel> optional = repository.findByBankId(bankId);
        BankModel update = optional.isEmpty() ? null : optional.get();
        if(update == null) {
            throw new CustomException("Bank does not exist");
        }
        else {
            update.setBankName(bank.getBankName());
            update.setBankSwiftCode(bank.getBankSwiftCode());
            repository.save(update);
        }
    }
}
