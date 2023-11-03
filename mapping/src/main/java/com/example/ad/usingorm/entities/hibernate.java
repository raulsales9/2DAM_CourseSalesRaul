package com.example.ad.usingorm.entities;

import java.io.File;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class hibernate implements Serializable {
    public static SessionFactory factory = null;

        static{
            try{
                factory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
            }catch(Throwable e){
                System.err.println("Error ");
                e.printStackTrace();
                throw new ExceptionInInitializerError(e);
            }
        }

        public static Session getSession(){
            return (Session) factory;
        }
}