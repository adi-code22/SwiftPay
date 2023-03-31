package com.stackroute.com.UserService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import io.jsonwebtoken.Claims;

import com.stackroute.com.UserService.exceptions.EmailIdAlreadyExistException;
import com.stackroute.com.UserService.exceptions.EmailIdNotExistException;
import com.stackroute.com.UserService.model.User;
import com.stackroute.com.UserService.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    //	registration of user
    //  business validation and business logic
    //  mobile number contains only numeric, name of the user valid etc
    public User saveUser(User user) throws EmailIdAlreadyExistException {
        Optional<User> optionalUser =  userRepository.findById(user.getEmailId());
        if(optionalUser.isPresent()) {
            throw new EmailIdAlreadyExistException("Invalid Email Id. Email Id Already Exist.");
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User updateUser(User user, User updateUser){
        updateUser.setEmailId(user.getEmailId());
        updateUser.setLocation(user.getLocation());
        updateUser.setNameOfTheUser(user.getNameOfTheUser());
        updateUser.setPassword(user.getPassword());
        updateUser.setMobileNumber(user.getMobileNumber());
        updateUser.setPanNumber(user.getPanNumber());
        updateUser.setProfilePassword(user.getProfilePassword());
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) throws EmailIdNotExistException {
        Optional<User> optionalUser = userRepository.findById(email);
        User user = optionalUser.isEmpty()?null:optionalUser.get();
        if(user == null){
            throw new EmailIdNotExistException("Email Id Not exist");
        }
        return user;
    }

    public boolean deleteUserByEmailId(String email) throws EmailIdNotExistException {
        Optional<User> optionalUser = userRepository.findById(email);
        User user = optionalUser.isEmpty()?null:optionalUser.get();
        if(user==null) {
            throw new EmailIdNotExistException("Email Id Not exist");
        }
        userRepository.deleteById(email);
        return true;
    }

    public boolean validateUser(User user) {
        Optional<User> optionalUser =  userRepository.findById(user.getEmailId());
        User userObj = optionalUser.isEmpty()?null:optionalUser.get();
        boolean isValid = false;
        if(userObj!=null && user.getPassword().equals(userObj.getPassword())) {
            isValid=true;
        }
        return isValid;
    }

}
