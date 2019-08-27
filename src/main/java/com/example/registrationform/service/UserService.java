package com.example.registrationform.service;

import com.example.registrationform.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
    List<User> getUserList();
    User get(int id);
    void delete (int id);
    void delete(User user);
    List<User> findAll();
    Optional<User> findById(int id);



}
