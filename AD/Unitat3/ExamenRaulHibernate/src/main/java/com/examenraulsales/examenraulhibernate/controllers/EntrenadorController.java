/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenraulsales.examenraulhibernate.controllers;

import com.examenraulsales.examenraulhibernate.entities.Entrenador;
import com.examenraulsales.examenraulhibernate.utils.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author rauls
 */
public class EntrenadorController {
     public EntrenadorController() {
    }
    
    public List<Object[]> getUsersByPage(int pageNumber, int pageSize) {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Query<Object[]> query = laSesion.createQuery("SELECT u.name, u.email FROM User u", Object[].class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            List<Object[]> userList = query.getResultList();
            laSesion.getTransaction().commit();
            return userList;
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error getting users by page. " + e.getMessage());
            return Collections.emptyList();
        } finally {
            laSesion.close();
        }
    }

    public List<Entrenador> getAllUsers() {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Query<Entrenador> query = laSesion.createQuery("FROM Entrenador", Entrenador.class);
            List<Entrenador> entrenadorList = query.getResultList();
            laSesion.getTransaction().commit();
            return entrenadorList;
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error getting all users. " + e.getMessage());
            return Collections.emptyList();
        } finally {
            laSesion.close();
        }
    }

    public void insert_user(Entrenador user) {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            laSesion.save(user);
            laSesion.getTransaction().commit();
            System.out.println("Entrenador inserted correctly");
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error inserting user. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }

    public void delete_user(Long userId) {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Entrenador user = laSesion.get(Entrenador.class, userId);
            if (user != null) {
                laSesion.delete(user);
                laSesion.getTransaction().commit();
                System.out.println("User deleted correctly");
            } else {
                System.out.println("User with ID " + userId + " not found");
            }
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error deleting user. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }

    public void updateUser(long id) {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Entrenador user = laSesion.get(Entrenador.class, id);

            // Establecer una contraseña fija
            //String newPassword = "nuevaContraseña";
            //user.setPassword(newPassword);

            // Guardar los cambios en la base de datos
            laSesion.saveOrUpdate(user);
            laSesion.getTransaction().commit();
            System.out.println("User updated correctly");
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            System.out.println("Error updating user. " + e.getMessage());
        } finally {
            laSesion.close();
        }
    }
}
