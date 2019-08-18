package com.example.registrationform.service;

import com.example.registrationform.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}
