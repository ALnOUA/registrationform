package com.example.registrationform.controller;

import com.example.registrationform.model.User;
import com.example.registrationform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Controller
@RequestMapping("/users")
public class UserProfileController {
    private final UserService userService;
    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid edition Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") int id, @Valid User user, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userService.saveUser(user);
        model.addAttribute("users", userService.findAll());
        return "users";
    }
    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        User  user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "users";

    }

}
