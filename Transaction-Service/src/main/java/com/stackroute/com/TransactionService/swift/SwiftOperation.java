package com.stackroute.com.TransactionService.swift;

import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.AbstractMT;
import com.prowidesoftware.swift.model.mt.mt1xx.MT101;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.prowidesoftware.swift.model.mt.mt9xx.MT900;
import com.prowidesoftware.swift.model.mt.mt9xx.MT910;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SwiftOperation {

    public String generateMT900(String message) throws IOException {
        AbstractMT abstractMT = AbstractMT.parse(message);
        MT101 mt101 = (MT101) abstractMT;
        MT900 mt900 = new MT900();
        mt900.addField(new Field20(mt101.getField20().getValue()));
        mt900.addField(new Field25(mt101.getSender()));
        mt900.addField(new Field21(mt101.getField21().get(0).asTag().getValue()));
        mt900.addField(new Field32A().setDate(mt101.getField30().getDate())
                .setCurrency(mt101.getField32B().get(0).getCurrency())
                .setAmount(mt101.getField32B().get(0).getAmount()));
        return mt900.message();
    }

    public String generateMT103FromMT101(String message) throws IOException {
        AbstractMT abstractMT = AbstractMT.parse(message);
        MT101 mt101 = (MT101) abstractMT;
        MT103 mt103 = new MT103();
        mt103.setSender(mt101.getSender());
        mt103.setReceiver(mt101.getReceiver());
        mt103.addField(new Field20(mt101.getField20().getValue()));
        mt103.addField(new Field23B("CRED"));
        mt103.addField(new Field32A().setDate(mt101.getField30().getDate())
                .setCurrency(mt101.getField32B().get(0).getCurrency())
                .setAmount(mt101.getField32B().get(0).getAmount()));
        mt103.addField(new Field50A(mt101.getField50F().get(0).getNameAndAddress1()));
//        mt103.addField(new Field59A(mt101.getField59A().get(0).getAccount()));
        mt103.addField(new Field70(mt101.getField21().get(0).getValue()));
        mt103.addField(new Field71A("SHA"));
        System.out.println("Done");
        return mt103.message();
    }

    public String generateMT910FromMT103(String message) throws IOException {
        System.out.println("Inside MT910");
        AbstractMT abstractMT = AbstractMT.parse(message);
        MT103 mt103 = (MT103) abstractMT;
        MT910 mt910 = new MT910();
        mt910.addField(new Field20(mt103.getField20().getValue()));
        mt910.addField(new Field21(mt103.getField70().getValue()));
        mt910.addField(new Field25(mt103.getReceiver()));
        mt910.addField(new Field32A().setDate(mt103.getField32A().getDate())
                .setCurrency(mt103.getField32A().getCurrency())
                .setAmount(mt103.getField32A().getAmount()));
        return mt910.message();
    }

}
