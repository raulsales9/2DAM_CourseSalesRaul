package com.example.ad.usingorm.controllers;

import com.example.ad.usingorm.entities.Usuario;
import org.hibernate.Session;


public class userscontroller {
    private Session lasesion; 

    public userscontroller(Session session) {
        this.lasesion = session;
    }
    public void inserUser(Usuario user) {
        try{
            lasesion.getTransaction().begin();
            lasesion.save(user);
            lasesion.getTransaction().commit();
            System.out.println("User inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            lasesion.close();
        }
    }

    public void updateUser(int userId, String name, String lastName, int age, String city) {
        try{
            lasesion.getTransaction().begin();
            Usuario user = lasesion.get(Usuario.class, 1);

            if(user != null){
                user.setNombre(name);
                user.setApellido(lastName);
                user.setEdad(age);
                user.setCiudad(city);
                lasesion.saveOrUpdate(user);
            }else{
                System.out.println("User not found");
            }
        } catch (Exception e) {
            if(lasesion.getTransaction() != null){
                lasesion.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            lasesion.close();
        }
    }
    public void deleteUser(Usuario user) {
        try{
            lasesion.getTransaction().begin();
            lasesion.delete(user);
            lasesion.getTransaction().commit();
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{  
            lasesion.close();   
        }
    }

    
}
