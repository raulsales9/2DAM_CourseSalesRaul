package com.example.testraul.demo.aplication.dtos;

import com.example.testraul.demo.domain.entitie.User;
import com.example.testraul.demo.aplication.dtos.userDto;

public class userDtoConverter {

    public static User convertToEntity(userDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setFechaRegistro(userDto.getFechaRegistro());
        return user;
    }

    public static userDto convertFromEntity(User user) {
        userDto userDto = new userDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setFechaRegistro(user.getFechaRegistro());
        return userDto;
    }

    public static userDto convertToDto(User user) {
        userDto userDto = new userDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setFechaRegistro(user.getFechaRegistro());
        return userDto;
    }
}
