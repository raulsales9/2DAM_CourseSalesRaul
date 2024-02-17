package com.example.testraul.demo.aplication.service.impl;

import com.example.testraul.demo.aplication.dtos.userDto;
import com.example.testraul.demo.aplication.dtos.userDtoConverter;
import com.example.testraul.demo.domain.entitie.User;
import com.example.testraul.demo.infraestructure.persistence.UserRepository;
import com.example.testraul.demo.aplication.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insertUser(userDto userDto) {
        User user = userDtoConverter.convertToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public userDto getUserById(long id) {
        User user = userRepository.findById(id).orElse(null);
        return (user != null) ? userDtoConverter.convertToDto(user) : null;
    }

    @Override
    public List<userDto> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<userDto> resultlist = new ArrayList<userDto>();
        for (int i = 0; i < userList.size(); ++i){
            resultlist.add(userDtoConverter.convertToDto(userList.get(i)));
        }
        return  resultlist;
    }
    @Override
    public userDto updateUser(userDto userDto) {
        User user = userDtoConverter.convertToEntity(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
