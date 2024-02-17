/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ieseljust.socialnetwork;

import com.ieseljust.DAO.UsersDAO;
import com.ieseljust.ORM.HibernateUtil;
import com.ieseljust.Model.Users;
import org.hibernate.Session;

/**
 *
 * @author samuel
 */
public class Socialnetwork {

    public static void main(String[] args) {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
//        SessionFactory sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).addAnnotatedClass(Users.class).buildSessionFactory();
//        Session laSesion = sessionFactory.getCurrentSession();

        //insert
        UsersDAO dao = new UsersDAO();
//        Users user = new Users("user1", "pass1");
//        Users user = new Users();
//        user.setUsername("kjcheiiehjd");
//        user.setPassword("12355");
//        dao.insertUser(user);

        //delete
        Users user1 = new Users();
        user1.setId(14);
        dao.deleteUser(user1);
//        update

//        dao.updateUser(1);
//        
//        dao.selectAll();

        
    }
}
