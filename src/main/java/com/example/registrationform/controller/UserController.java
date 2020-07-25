package com.example.registrationform.controller;

import javax.validation.Valid;

import com.example.registrationform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.registrationform.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping(value= {"login"})
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("/login");
        return model;
    }

    @GetMapping(value= {"signup"})
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("/signup");

        return model;
    }

    @PostMapping(value= {"signup"})
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("/signup");
        }

        return model;
    }

    @GetMapping(value= {"home"})
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        if(user.getActive()==2) {
            model.setViewName("user/user-home");
        }
        else if( user.getActive()==1){
            model.setViewName("admin/admin-home");
        }
        return model;
    }

    @GetMapping(value= {"/access_denied"})
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
    @GetMapping(value="/templates/users")
    public String contacts(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "users";
    }

    @GetMapping(value= { "/","/main"})
    public ModelAndView main() {
        ModelAndView model = new ModelAndView();

        model.setViewName("main");
        return model;
    }

}