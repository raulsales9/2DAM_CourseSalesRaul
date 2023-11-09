
package com.example.ad.usingorm.controllers;

import com.example.ad.usingorm.entities.Direccion;
import org.hibernate.Session;
/**
 *
 * @author pc-raul
 */
public class DireccionController {
    private final Session session;

    public DireccionController(Session session) {
        this.session = session;
    }

    public void insertDireccion(Direccion direccion) {
        try {
            session.beginTransaction();
            session.save(direccion);
            session.getTransaction().commit();
            System.out.println("Direccion inserted successfully");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
