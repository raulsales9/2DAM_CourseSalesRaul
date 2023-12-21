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
import postgre_obj.utils.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Iniciar una transacción
        session.beginTransaction();

        // Realizar la consulta
        List<Asignatura> asignaturas = session.createQuery("FROM Asignatura").list();

        // Imprimir los resultados
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura);
        }

        // Finalizar la transacción
        session.getTransaction().commit();

        // Cerrar la Session
        session.close();
    }
}

    