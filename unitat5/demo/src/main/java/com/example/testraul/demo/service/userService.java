package com.example.testraul.demo.service;

import com.example.testraul.demo.dtos.userDto;
import com.example.testraul.demo.entitie.User;
import com.example.testraul.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService implements userServiceInterface {

    @Autowired
    private userRepository userRepository;

    @Override
    public void insertUser(userDto userDto){
        User user = userDto.convertToEntity(userDto);
        userRepository.save(user);
    }
}
