package com.example.ad.usingorm.controllers;

import com.example.ad.usingorm.entities.DetallesUsuario;
import org.hibernate.Session;
/**
 *
 * @author pc-raul
 */
public class DetallesUsuarioController {
    private final Session session;

    public DetallesUsuarioController(Session session) {
        this.session = session;
    }

    public void insertDetallesUsuario(DetallesUsuario detallesUsuario) {
        try {
            session.beginTransaction();
            session.save(detallesUsuario);
            session.getTransaction().commit();
            System.out.println("DetallesUsuario inserted successfully");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}

