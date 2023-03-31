package com.stackroute.com.BankService.interservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.com.BankService.model.TransactionModel;
import com.stackroute.com.BankService.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class InterService {


    public User getUserDetails(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        String uri = "http://localhost:8090/user-service/users/verify/";
        ResponseEntity<User> entity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, User.class);
        return entity.getBody();
    }

    public TransactionModel initiateTransaction(TransactionModel model) throws JsonProcessingException {
        System.out.println(model.toString());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TransactionModel> httpEntity = new HttpEntity<TransactionModel>(model, headers);
        String uri = "http://localhost:8060/transaction-service/transfer";
        ResponseEntity<TransactionModel> entity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, TransactionModel.class);
        System.out.println("***********Inside initiate transaction 8****************" + entity.getBody().toString());
        return entity.getBody();
    }

}
