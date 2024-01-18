package com.example.testraul.demo.dtos;

import com.example.testraul.demo.entitie.User;

import java.util.Date;

public class userDto {

    public User convertToEntity(userDto userDto) {
        User user = new User();
        user.setID(userDto.getIDUser());
        user.setName(userDto.getNameUser());
        return user;
    }

    private int getIDUser() {
        return 0;
    }

    private String getNameUser(email) {
        return null;
    }
}
