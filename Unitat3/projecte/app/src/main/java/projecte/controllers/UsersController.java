package projecte.controllers;

import com.example.ad.usingorm.utils.HibernateUtil;
import projecte.entities.User;
import org.hibernate.Transaction;
import org.hibernate.Session;

/**
 *
 * @author pc-raul
 */
public class UsersController {
    Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();

    public UsersController() {

    }
    
    public void insert_user(User user){
        try{
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
    
    public void delete_user(User user){
        try{
            laSesion.getTransaction().begin();
            laSesion.delete(user);
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
    
    public void updateUser(int id){
        try {
            laSesion.getTransaction().begin();

            User usertoupdate = laSesion.get(User.class, id);

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
}

