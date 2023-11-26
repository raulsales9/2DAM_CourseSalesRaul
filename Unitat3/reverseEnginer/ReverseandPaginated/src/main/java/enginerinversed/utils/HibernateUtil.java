package enginerinversed.utils;

import java.io.File;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
/**
 *
 * @author Fernando
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
       
       static {
           try {
               sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
   
           } catch (Throwable ex) {
               System.err.println("Error en la inicialización. " + ex);
               throw new ExceptionInInitializerError(ex);
           }
       }
   
       public static SessionFactory getSessionFactory() {
           return sessionFactory;
       }
   
}
