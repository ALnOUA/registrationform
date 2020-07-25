package com.example.registrationform.repository;

import com.example.registrationform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);

    void delete(User user);

    @Override
    List<User> findAll();
}