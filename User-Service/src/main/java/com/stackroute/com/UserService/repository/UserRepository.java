package com.stackroute.com.UserService.repository;

import javax.transaction.Transactional;

import com.stackroute.com.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {}


