package com.example.testraul.demo.controllers;

import com.example.testraul.demo.entitie.User;
import com.example.testraul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a single user
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    // Update a user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Integer userId, @RequestBody User userDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setFechaRegistro(userDetails.getFechaRegistro());

        return userRepository.save(user);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
