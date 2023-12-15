/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.examenraulsales.examenraulhibernate;

import com.examenraulsales.examenraulhibernate.controllers.EntrenadorController;
import com.examenraulsales.examenraulhibernate.entities.Competicion;
import com.examenraulsales.examenraulhibernate.entities.Entrenador;
import com.examenraulsales.examenraulhibernate.entities.Equipo;
import com.examenraulsales.examenraulhibernate.entities.Jugar;
import com.examenraulsales.examenraulhibernate.utils.HibernateUtil;
import static com.examenraulsales.examenraulhibernate.utils.HibernateUtil.getSessionFactory;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author rauls
 */
public class ExamenRaulHibernate {
    public static void main(String[] args) {
       // try {
       //         DatabaseMetaData metaData = connection.getMetaData();
       //         ResultSet tables = metaData.getTables(null, null, "%", null);
       //         while (tables.next()) {
       //             System.out.println(tables.getString(3));
       //         }
       //     } catch (SQLException e) {
       //         e.printStackTrace();
       //     }
       
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Entrenador");


            int option = scanner.nextInt();

            if (option == 0) {
                System.out.println("Saliendo...");
                break;
            }

            switch (option) {
                case 1:
                    performUserOperations();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

   private static void performUserOperations() {
    EntrenadorController entrenadorController = new EntrenadorController();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Seleccione una operación para Entrenador:");
    System.out.println("1. Listar todos los entrenadores");
    System.out.println("2. Insertar nuevo entrenador");
    System.out.println("3. Actualizar entrenador");
    System.out.println("4. Borrar entrenador");

    int userOption = scanner.nextInt();

    switch (userOption) {
        case 1:
            List<Entrenador> entrenadores = entrenadorController.getAllUsers();
            for (Entrenador entrenador : entrenadores) {
                System.out.println(entrenador);
            }
            break;
        case 2:
            Entrenador nuevoEntrenador = new Entrenador();
            System.out.println("Introduce el nombre del entrenador:");
            String name = scanner.next();
            nuevoEntrenador.setName(name);
            // Aquí puedes agregar más campos para establecer en el nuevoEntrenador
            entrenadorController.insert_user(nuevoEntrenador);
            break;
        case 3:
            System.out.println("Introduce el ID del entrenador a actualizar:");
            long id = scanner.nextLong();
            Entrenador entrenadorAActualizar = (Entrenador) entrenadorController.getAllUsers();
            if (entrenadorAActualizar != null) {
                System.out.println("Introduce el nuevo nombre del entrenador:");
                String newName = scanner.next();
                entrenadorAActualizar.setName(newName);
                // Aquí puedes agregar más campos para actualizar en el entrenadorAActualizar
                entrenadorController.updateUser(id);
            } else {
                System.out.println("No se encontró ningún entrenador con el ID " + id);
            }
            break;
        case 4:
            System.out.println("Introduce el ID del entrenador a borrar:");
            long idBorrar = scanner.nextLong();
            entrenadorController.delete_user(idBorrar);
            break;
        default:
            System.out.println("Opción no válida. Intente de nuevo.");
    }
}

}
