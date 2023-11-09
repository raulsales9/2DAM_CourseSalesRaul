package com.example.ad.usingorm.controllers;

import com.example.ad.usingorm.entities.Usuario;
import org.hibernate.Session;

public class UserController {
    private Session laSesion;

    public UserController(Session session) {
        this.laSesion = session;
    }

    public void insertUser(Usuario user) {
        try {
            laSesion.save(user);
            System.out.println("User inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int userId, String name, String lastName, int age, String city) {
        try {
            Usuario user = laSesion.get(Usuario.class, userId);

            if (user != null) {
                user.setNombre(name);
                user.setApellido(lastName);
                user.setEdad(age);
                user.setCiudad(city);
                laSesion.saveOrUpdate(user);
                System.out.println("User updated successfully");
            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Usuario user) {
        try {
            laSesion.delete(user);
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
