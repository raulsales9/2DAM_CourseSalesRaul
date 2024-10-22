/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecte.controllers;

/**
 *
 * @author pc-raul
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import projecte.entities.Posts;
import projecte.utils.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.query.Query;

public class PostsController {

    public PostsController() {
    }
    public List<Posts> findAllPosts() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            Query<Posts> query = session.createQuery("FROM Posts", Posts.class);
            List<Posts> postsList = query.getResultList();
            session.getTransaction().commit();
            return postsList;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error getting all posts. " + e.getMessage());
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public void updatePost(Posts post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(post);
            transaction.commit();
            System.out.println("Post updated successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error updating post. " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void deletePost(Long postId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Posts post = session.get(Posts.class, postId);
            if (post != null) {
                session.delete(post);
                transaction.commit();
                System.out.println("Post deleted successfully");
            } else {
                System.out.println("Post with ID " + postId + " not found");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error deleting post. " + e.getMessage());
        } finally {
            session.close();
        }
    }
    public void insertPost(Posts post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            System.out.println("Post inserted successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error inserting post. " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
