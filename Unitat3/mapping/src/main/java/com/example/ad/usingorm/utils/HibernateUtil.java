package com.example.ad.usingorm.utils;

import java.io.File;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
/**
 *
 * @author Fernando
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    // Código estático. Solo se ejecuta una vez, como un Singleton
    static {
        try {
            // Creamos SessionFactory desde el archivo hibernate.cfg.xml
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
