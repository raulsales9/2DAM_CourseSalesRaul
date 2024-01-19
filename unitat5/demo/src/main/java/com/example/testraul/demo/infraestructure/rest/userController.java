package com.example.testraul.demo.infraestructure.rest;

import com.example.testraul.demo.aplication.dtos.userDto;
import com.example.testraul.demo.aplication.service.impl.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private userServiceImpl userServiceImpl;

    @GetMapping("/Showform")
    public String showForm(Model model) {
        model.addAttribute("userDto", new userDto());
        return "mainpage";
    }
    @GetMapping("/allUsers")
    public String getAllUsers(Model model) {
        List<userDto> users = userServiceImpl.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }
    // Create a new user
    @GetMapping("/insertuser")
    public String showInsertUserForm(Model model) {
        model.addAttribute("userDto", new userDto());
        return "userInsert";
    }

    @PostMapping("/insertuser")
    public String createUser(@ModelAttribute userDto userDto) {
        userServiceImpl.insertUser(userDto);
        return "redirect:/api/users/allUsers";
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }



    @GetMapping("/userForm/{id}")
    public String showUserForm(@PathVariable int id, Model model) {
        userDto userDto = userServiceImpl.getUserById(id);
        if (userDto == null) {
            return "error";
        }
        model.addAttribute("userDto", userDto);
        return "userChange";
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@ModelAttribute userDto userDto) {
        userServiceImpl.updateUser(userDto);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }
}
