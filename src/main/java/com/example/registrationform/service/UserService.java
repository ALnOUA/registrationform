package com.example.registrationform.service;

import com.example.registrationform.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
    List<User> getUserList();


}
