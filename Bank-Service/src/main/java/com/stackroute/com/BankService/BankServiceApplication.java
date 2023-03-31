package com.stackroute.com.BankService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}

}  // mt101.addField(new Field20(generateRandom("MT101")));
    // //
    // private String generateMT103(TransactionModel model) {
    //     Random random = new Random();
    //     int randomNumber = random.nextInt(9000) + 1000;
    //     int randomNumber2 = random.nextInt(900000) + 100000;
    //     MT103 mt103 = new MT103();

    //     SwiftBlock1 b1 = new SwiftBlock1();
    //     b1.setApplicationId("F");
    //     b1.setServiceId("01");
    //     b1.setLogicalTerminal(model.getRecieverSwiftCode());
    //     b1.setSessionNumber(String.valueOf(random.nextInt(9000) + 1000));
    //     b1.setServiceId(String.valueOf(randomNumber2));

    //      mt103.setSender(model.getSenderAccountNumber().getAccountNumber());
    //      mt103.setReceiver(model.getReceiverAccountNumber());
    //      mt103.addField(new Field20(generateRandom("MT103")));
    //      Field50a field50a = new Field50a()
    //      Field59F field59F = new Field59F();
    //      field59F.setNameAndAddress1(model.getReceiverLocation());
    //      mt103.addField(field59F);
    //      mt103.addField(new Field71A("SHA"));
    //     Field32A f32A = new Field32A()
    //             .setDate(Calendar.getInstance())
    //             .setCurrency("USD")
    //             .setAmount(model.getDebit());
    //     mt103.addField(f32A);
    //     return mt103.message();

    // }
