

package com.example.ad.usingorm.controllers;

import com.example.ad.usingorm.entities.Publicacion;
import org.hibernate.Session;
/**
 *
 * @author pc-raul
 */
public class PublicationController {
    private Session laSesion;

    public PublicationController(Session session) {
        this.laSesion = session;
    }

    public void insertPublication(Publicacion publicacion) {
        try {
            laSesion.getTransaction().begin();
            laSesion.save(publicacion);
            laSesion.getTransaction().commit();
            System.out.println("Publicacion inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            laSesion.close();
        }
    }

    public void updatePublication(int publicationId, String content) {
        try {
            laSesion.getTransaction().begin();
            Publicacion publicacion = laSesion.get(Publicacion.class, publicationId);

            if (publicacion != null) {
                publicacion.setContenido(content);
                laSesion.saveOrUpdate(publicacion);
            } else {
                System.out.println("Publication not found");
            }
        } catch (Exception e) {
            if (laSesion.getTransaction() != null) {
                laSesion.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            laSesion.close();
        }
    }

    public void deletePublication(Publicacion publicacion) {
        try {
            laSesion.getTransaction().begin();
            laSesion.delete(publicacion);
            laSesion.getTransaction().commit();
            System.out.println("Publication deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            laSesion.close();
        }
    }
}

