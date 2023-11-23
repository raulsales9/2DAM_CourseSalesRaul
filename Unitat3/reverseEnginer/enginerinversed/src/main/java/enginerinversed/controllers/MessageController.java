/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enginerinversed.controllers;

/**
 *
 * @author pc-raul
 */

import enginerinversed.utils.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import enginerinversed.entities.*;

public class MessageController {
     public List<Message> getAllMessages() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query<Message> query = session.createQuery("FROM Message", Message.class);
            List<Message> messageList = query.getResultList();
            session.getTransaction().commit();
            return messageList;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error getting all messages. " + e.getMessage());
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public void insertMessage(Message message) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(message);
            session.getTransaction().commit();
            System.out.println("Message inserted correctly");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error inserting message. " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void deleteMessage(Long messageId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Message message = session.get(Message.class, messageId);
            if (message != null) {
                session.delete(message);
                session.getTransaction().commit();
                System.out.println("Message deleted correctly");
            } else {
                System.out.println("Message with ID " + messageId + " not found");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error deleting message. " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateMessage(long messageId, Message updatedMessage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Message message = session.get(Message.class, messageId);
            if (message != null) {
                message.setText(updatedMessage.getText());
                message.setUserBySenderIduser(updatedMessage.getUserBySenderIduser());
                session.getTransaction().commit();
                System.out.println("Message updated correctly");
            } else {
                System.out.println("Message with ID " + messageId + " not found");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error updating message. " + e.getMessage());
        } finally {
            session.close();
        }
    }

}
