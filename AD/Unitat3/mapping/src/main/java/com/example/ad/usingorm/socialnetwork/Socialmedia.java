package com.example.ad.usingorm.socialnetwork;

import org.hibernate.Session;
import com.example.ad.usingorm.entities.*;
import com.example.ad.usingorm.utils.HibernateUtil;
import com.example.ad.usingorm.controllers.*;

/**
 *
 * @author pc-raul
 */
class Socialmedia {
    public static void main(String[] args) {
        try (Session laSesion = HibernateUtil.getSessionFactory().openSession()) {

            UserController userController = new UserController(laSesion);

            Usuario usuario = new Usuario("Nombre", "Apellido", 30, "Ciudad", null, null);
            Usuario usuario2 = new Usuario("Nombre", "Apellido", 30, "Ciudad", null, null);
            userController.insertUser(usuario);
            userController.insertUser(usuario2);

            userController.updateUser(1, "NuevoNombre", "NuevoApellido", 35, "NuevaCiudad");

            userController.deleteUser(usuario);

            PublicationController publicationController = new PublicationController(laSesion);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


