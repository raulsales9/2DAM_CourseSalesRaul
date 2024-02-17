/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.DAO;

import com.ieseljust.ORM.HibernateUtil;
import com.ieseljust.Model.Users;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author samuel
 */
public class UsersDAO {

    Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();

    public UsersDAO() {

    }

    //insert
    public void insertUser(Users user) {
        try {
            laSesion.getTransaction().begin();
            laSesion.save(user);
            laSesion.getTransaction().commit();
            System.out.println("User inserted correctly");
        } catch (Exception e) {
            laSesion.getTransaction().rollback();
            System.out.println("Error inserting user. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }

    //delete
    public void deleteUser(Users user) {
        try {
            laSesion.getTransaction().begin();
            laSesion.delete(user);
            laSesion.getTransaction().commit();
            System.out.println("User deleted corrected");
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error inserting user. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }

    //update
    public void updateUser(int id) {
        try {
            laSesion.getTransaction().begin();

            Users usertoupdate = laSesion.get(Users.class, id);

            usertoupdate.setPassword("99999");

            laSesion.saveOrUpdate(usertoupdate);

            laSesion.getTransaction().commit();

            System.out.println("User updated correctly");
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error inserting user. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }

    public void selectAll() {
        try {
            laSesion.getTransaction().begin();


            laSesion.getTransaction().commit();

            System.out.println("User updated correctly");
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error inserting user. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }
}
