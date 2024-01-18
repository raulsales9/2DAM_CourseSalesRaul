package com.example.testraul.demo.controllers;

import com.example.testraul.demo.entitie.User;
import com.example.testraul.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private userRepository userRepository;

    @GetMapping("/Showform")
    public String showform(){
        return "mainpage";
    }
    // Create a new user
    @PostMapping("/")
    public User createUser(@RequestParam User user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
