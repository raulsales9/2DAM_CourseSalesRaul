/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enginerinversed.controllers;

import enginerinversed.utils.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import enginerinversed.entities.*;

/**
 *
 * @author pc-raul
 */
public class EventsController {

    public List<Event> getAllEvents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query<Event> query = session.createQuery("SELECT e FROM Event e WHERE e.name IS NOT NULL", Event.class);
            List<Event> eventList = query.getResultList();
            session.getTransaction().commit();
            return eventList;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error getting all events. " + e.getMessage());
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public void insertEvent(Event event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(event);
            session.getTransaction().commit();
            System.out.println("Event inserted correctly");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error inserting event. " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void deleteEvent(Long eventId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Event event = session.get(Event.class, eventId);
            if (event != null) {
                session.delete(event);
                session.getTransaction().commit();
                System.out.println("Event deleted correctly");
            } else {
                System.out.println("Event with ID " + eventId + " not found");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error deleting event. " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateEvent(long eventId, Event updatedEvent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Event event = session.get(Event.class, eventId);
            if (event != null) {
                event.setName(updatedEvent.getName());
                event.setStartDate(updatedEvent.getStartDate());
                session.saveOrUpdate(event);
                session.getTransaction().commit();
                System.out.println("Event updated correctly");
            } else {
                System.out.println("Event with ID " + eventId + " not found");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error updating event. " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
