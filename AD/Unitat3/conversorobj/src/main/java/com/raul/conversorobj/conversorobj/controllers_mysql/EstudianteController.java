/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raul.conversorobj.conversorobj.controllers_mysql;

import com.raul.conversorobj.conversorobj.entities_mysql.Estudiante;
import com.raul.conversorobj.conversorobj.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author rauls
 */


public class EstudianteController {

    public void agregarEstudiante(Estudiante estudiante) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(estudiante);
                transaction.commit();
            }
        }
    }

    public Estudiante obtenerEstudiante(int estudianteId) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            try (Session session = factory.openSession()) {
                return session.get(Estudiante.class, estudianteId);
            }
        }
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(estudiante);
                transaction.commit();
            }
        }
    }

    public void eliminarEstudiante(int estudianteId) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                Estudiante estudiante = session.get(Estudiante.class, estudianteId);
                if (estudiante != null) {
                    session.delete(estudiante);
                }
                transaction.commit();
            }
        }
    }
}
