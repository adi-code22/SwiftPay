package com.stackroute.com.UserService.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stackroute.com.UserService.exceptions.EmailIdAlreadyExistException;
import com.stackroute.com.UserService.exceptions.EmailIdNotExistException;
import com.stackroute.com.UserService.model.User;
import com.stackroute.com.UserService.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Application;

@RestController
//@CrossOrigin("*")
@RequestMapping("user-service")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public ResponseEntity<?> home(){
        ResponseEntity<?> entity = new ResponseEntity<String>("Welcome to SwiftPay",HttpStatus.OK);
        return entity;
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> getAllUsers(){
        List<User> userList = userServiceImpl.getAllUser();
        ResponseEntity<?> entity = new ResponseEntity<List<User>>(userList,HttpStatus.OK);
        return entity;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUer(@RequestBody User user){
        ResponseEntity<?> entity = null;
        String regexPattern = "^(.+)@(\\S+)$";
        try {
            if(user.getEmailId() == null || user.getPassword() == null ){
                return new ResponseEntity<String>("Important Information Missing", HttpStatus.BAD_REQUEST);
            }else if(!user.getEmailId().matches(regexPattern)){
                return new ResponseEntity<String>("Bad Email Id", HttpStatus.BAD_REQUEST);
            }
            userServiceImpl.saveUser(user);
            entity= new ResponseEntity<String>("User Registered Successfully..",HttpStatus.CREATED);
        } catch (EmailIdAlreadyExistException e) {
            entity= new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return entity;
    }


    @GetMapping(value="/users/{email}", produces = "application/json")
    public ResponseEntity<?> getUserByEmailId(@PathVariable("email") String emailId) {
        ResponseEntity<?> entity = null;
        User user = null;
        try {
            user = userServiceImpl.getUserByEmail(emailId);
        } catch (EmailIdNotExistException e) {
            throw new RuntimeException(e);
        }
        if(user==null) {
            entity = new ResponseEntity<String>("Email Id Not Exist",HttpStatus.BAD_REQUEST);
        }else {
            entity = new ResponseEntity<User>(user,HttpStatus.OK);
        }
        return entity;
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable("email") String emailId){
        boolean isDeleted = false;
        try {
            isDeleted = userServiceImpl.deleteUserByEmailId(emailId);
        } catch (EmailIdNotExistException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<?> entity = new ResponseEntity<String>("Something went Wrong",HttpStatus.BAD_REQUEST);
        if(isDeleted) {
            entity = new ResponseEntity<String>("User deleted Successfully",HttpStatus.OK);
        }
        return entity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> validateUser(@RequestBody User user){
        boolean isValid = userServiceImpl.validateUser(user);
        ResponseEntity<?> entity = new ResponseEntity<String>("Invalid username or password",HttpStatus.UNAUTHORIZED);;
        if (isValid) {
            String token = getToken(user.getEmailId());
            entity = new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return entity;
    }


    private String getToken(String emailId) {
        String token = Jwts.builder().setSubject(emailId).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "success").compact();
        return token;
    }

    private String validateToken(String token){
        final Claims claims = Jwts.parser().setSigningKey("success").parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    //PUT for User details editing
    @PutMapping("users/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("email") String emailId, @RequestBody User user) {
        User updateUser;
        try {
            updateUser = userServiceImpl.getUserByEmail(emailId);
        } catch (EmailIdNotExistException e) {
            throw new RuntimeException(e);
        }
        userServiceImpl.updateUser(user, updateUser);
        return new ResponseEntity<User>(updateUser, HttpStatus.CREATED);
    }

    @GetMapping(value="/users/verify", produces = "application/json")
    public ResponseEntity<?> verifyUser(@RequestHeader Map<String, String> header){
        Claims claims = Jwts.parser().setSigningKey("success").parseClaimsJws(header.get("token").toString()).getBody();
        ResponseEntity<?> entity = null;
        User user = null;
        if(claims.isEmpty()){
            return new ResponseEntity<String>("Bad JWT Token", HttpStatus.UNAUTHORIZED);
        }else{
            try {
                user = userServiceImpl.getUserByEmail(claims.getSubject());
            } catch (EmailIdNotExistException e) {

                throw new RuntimeException(e);
            }
            if(user==null) {
                entity = new ResponseEntity<String>("Email Id Not Exist",HttpStatus.BAD_REQUEST);
            }else {
                entity = new ResponseEntity<User>(user,HttpStatus.OK);
            }
            return entity;
        }
    }

    @ExceptionHandler(EmailIdNotExistException.class)
    public ResponseEntity<?> exceptionHandler(Exception e){
        ResponseEntity<?> entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        return entity;
    }



    // THIS IS A CUSTOM FUNCTION FOR TESTING INTER SERVICE COMMUNICATION WITH BANK SERVICE
    @GetMapping("/test/{token}")
    public ResponseEntity<?> test(@PathVariable String token) throws EmailIdNotExistException {
        String email = validateToken(token);
        User user = userServiceImpl.getUserByEmail(email);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
