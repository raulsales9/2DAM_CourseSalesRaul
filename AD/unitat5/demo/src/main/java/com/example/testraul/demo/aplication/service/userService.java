package com.example.testraul.demo.aplication.service;

import com.example.testraul.demo.aplication.dtos.userDto;

import java.util.List;

public interface userService {
    void insertUser(userDto userDto);
    userDto getUserById(long id);
    List<userDto> getAllUsers();

    userDto updateUser(userDto userDto);
    void deleteUser(long id);
}
