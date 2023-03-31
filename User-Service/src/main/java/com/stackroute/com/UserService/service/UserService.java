package com.stackroute.com.UserService.service;

import com.stackroute.com.UserService.exceptions.EmailIdAlreadyExistException;
import com.stackroute.com.UserService.exceptions.EmailIdNotExistException;
import com.stackroute.com.UserService.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user) throws EmailIdAlreadyExistException;

    public List<User> getAllUser();

    public User getUserByEmail(String email) throws EmailIdNotExistException;

    public boolean deleteUserByEmailId(String email) throws EmailIdNotExistException;

    public boolean validateUser(User user);

    public User updateUser(User user, User updateUser);
}
