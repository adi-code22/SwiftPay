package com.stackroute.com.BankService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String emailId;
    private String password;
    private String nameOfTheUser;
    private String mobileNumber;
    private String location;
    private String panNumber;
    private String profilePassword;
}
