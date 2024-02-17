package projecte.controllers;

import projecte.utils.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.query.Query;
import projecte.entities.User;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class UsersController {

    public UsersController() {
    }

    public List<User> getAllUsers() {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            Query<User> query = laSesion.createQuery("FROM User", User.class); // Cambiado de "FROM user" a "FROM User"
            List<User> userList = query.getResultList();
            laSesion.getTransaction().commit();
            return userList;
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

    public void insert_user(User user) {
        Session laSesion = HibernateUtil.getSessionFactory().openSession();
        try {
            laSesion.getTransaction().begin();
            laSesion.save(user);
            laSesion.getTransaction().commit();
            System.out.println("User inserted correctly");
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
            User user = laSesion.get(User.class, userId);
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
            User user = laSesion.get(User.class, id);
            user.setPassword("99999");
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
