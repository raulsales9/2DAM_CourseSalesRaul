package com.example.testraul.demo.infraestructure.rest;

import com.example.testraul.demo.aplication.dtos.userDto;
import com.example.testraul.demo.aplication.service.impl.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "/user/userList";
    }

    // Create a new user
    @GetMapping("/insertuser")
    public String showInsertUserForm(Model model) {
        model.addAttribute("userDto", new userDto());
        return "/user/userInsert";
    }

    @PostMapping("/insertuser")
    public String createUser(@ModelAttribute userDto userDto) {
        userServiceImpl.insertUser(userDto);
        return "redirect:/api/users/allUsers";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable long id) {
        userServiceImpl.deleteUser(id);
        // Redirige a la ruta '/api/users/allUsers'
        return "redirect:/api/users/allUsers";
    }

    @GetMapping("/userForm/{id}")
    public String showUserForm(@PathVariable long id, Model model) {
        userDto userDto = userServiceImpl.getUserById(id);
        if (userDto == null) {
            return "error";
        }
        model.addAttribute("userDto", userDto);
        return "/user/userChange";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute userDto userDto) {
        userServiceImpl.updateUser(userDto);
        return "redirect:/api/users/allUsers";
    }
}
