package org.raul.conversor;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.raul.conversor.entities_mysql.Asignatura;
import org.raul.conversor.entities_mysql.Aula;
import org.raul.conversor.entities_mysql.Curso;
import org.raul.conversor.entities_mysql.Estudiante;

public class Main {
    public static void main(String[] args) {
        // Crear EntityManagerFactory para la base de datos relacional
        EntityManagerFactory emfRelacional = Persistence.createEntityManagerFactory("miUnidadDePersistenciaRelacional");
        EntityManager emRelacional = emfRelacional.createEntityManager();

        // Iniciar transacción
        emRelacional.getTransaction().begin();

        // Consulta para obtener todos los estudiantes
        List<Estudiante> estudiantes = emRelacional.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();

        // Cerrar transacción
        emRelacional.getTransaction().commit();

        // Cerrar EntityManager y EntityManagerFactory
        emRelacional.close();
        emfRelacional.close();

        // Crear EntityManagerFactory para ObjectDB
        EntityManagerFactory emfObjectDB = Persistence.createEntityManagerFactory("$objectdb/db/miBaseDeDatos.odb");
        EntityManager emObjectDB = emfObjectDB.createEntityManager();

        // Iniciar transacción
        emObjectDB.getTransaction().begin();

        // Almacenar todos los estudiantes en la base de datos ObjectDB
        for (Estudiante estudiante : estudiantes) {
            emObjectDB.persist(estudiante);
        }

        // Confirmar la transacción
        emObjectDB.getTransaction().commit();

        // Cerrar EntityManager y EntityManagerFactory
        emObjectDB.close();
        emfObjectDB.close();
    }
}

    