package com.stackroute.com.BankService.swift;

import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt1xx.MT101;
import com.stackroute.com.BankService.model.TransactionModel;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SwiftOperation {
    public String generateMT101(TransactionModel model) {
        MT101 mt101 = new MT101();
        mt101.setSender(model.getSenderAccountNumber());
        mt101.setReceiver(model.getReceiverAccountNumber());
        mt101.addField(new Field20(generateRandom("MT101")));
        mt101.addField(new Field28D("1/1"));
        mt101.addField(new Field30(generateDate()));
        mt101.addField(new Field21(generateRandom("")));
        Field32B fieldF32B = new Field32B();
        fieldF32B.setCurrency("USD");
        fieldF32B.setAmount(model.getDebit());
        mt101.addField(fieldF32B);
        Field50F field50F = new Field50F();
        field50F.setNameAndAddress1(model.getSenderLocation());
        mt101.addField(field50F);
        mt101.addField(new Field52A(generateRandom("")));
        Field59A field59A = new Field59A();
        field59A.setAccount(model.getReceiverAccountNumber());
        field59A.setComponent1(model.getReceiverLocation());
        mt101.addField(new Field71A("SHA"));
        return mt101.message();
    }

    private String generateRandom(String s) {
        return s + RandomStringUtils.randomAlphanumeric(5);
    }

    private String generateDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        String date = today.format(dateTimeFormatter).replace("/", "");
        return date;
    }
}
