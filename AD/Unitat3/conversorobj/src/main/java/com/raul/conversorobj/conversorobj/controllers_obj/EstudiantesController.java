/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raul.conversorobj.conversorobj.controllers_obj;

import com.raul.conversorobj.conversorobj.entities_obj.Estudiantes;
import com.raul.conversorobj.conversorobj.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author rauls
 */
public class EstudiantesController {
    public void agregarEstudiante(Estudiantes estudiante) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(estudiante);
                transaction.commit();
            }
        }
    }

    public Estudiantes obtenerEstudiante(int estudianteId) {
        try (SessionFactory factory = HibernateUtil.getSessionFactory()) {
            try (Session session = factory.openSession()) {
                return session.get(Estudiantes.class, estudianteId);
            }
        }
    }

    public void actualizarEstudiante(Estudiantes estudiante) {
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
                Estudiantes estudiante = session.get(Estudiantes.class, estudianteId);
                if (estudiante != null) {
                    session.delete(estudiante);
                }
                transaction.commit();
            }
        }
    }
}

