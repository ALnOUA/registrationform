package com.example.registrationform.service;

import com.example.registrationform.model.Role;
import com.example.registrationform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.registrationform.repository.RoleRespository;
import com.example.registrationform.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRespository roleRespository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(2);
        Role userRole = roleRespository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }
    @Override
    public User get(int id) {
        return userRepository.findById(id).get();
    }
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }


}


