package org.raul.conversor;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.raul.conversor.entities_mysql.Asignatura;
import org.raul.conversor.entities_mysql.Aula;
import org.raul.conversor.entities_mysql.Curso;

public class Main {
    public static void main(String[] args) {
    
        
        // Configuración de la conexión a la base de datos MySQL
        Configuration mysqlConfig = new Configuration();
        mysqlConfig.configure("hibernate.cfg.xml"); // Asegúrate de que este archivo exista y tenga la configuración correcta
        SessionFactory mysqlSessionFactory = mysqlConfig.buildSessionFactory();

        // Configuración de la conexión a la base de datos ObjectDB
        Configuration objectDbConfig = new Configuration();
        objectDbConfig.configure("objectdb.cfg.xml"); // Asegúrate de que este archivo exista y tenga la configuración correcta
        SessionFactory objectDbSessionFactory = objectDbConfig.buildSessionFactory();

        // Crea una sesión de Hibernate para MySQL
        Session mysqlSession = mysqlSessionFactory.openSession();

        // Crea una sesión de Hibernate para ObjectDB
        Session objectDbSession = objectDbSessionFactory.openSession();

        // Inicia una transacción en MySQL
        mysqlSession.beginTransaction();

        // Obtiene todas las entidades de MySQL
        List<Asignatura> asignaturas = (List<Asignatura>) mysqlSession.createQuery("from Asignatura").list();
        List<Aula> aulas = (List<Aula>) mysqlSession.createQuery("from Aula").list();
        List<Curso> cursos = (List<Curso>) mysqlSession.createQuery("from Curso").list();

        // Finaliza la transacción en MySQL
        mysqlSession.getTransaction().commit();

        // Inicia una transacción en ObjectDB
        objectDbSession.beginTransaction();

        // Inserta todas las entidades en ObjectDB
        for (Asignatura asignatura : asignaturas) {
            objectDbSession.save(asignatura);
        }
        for (Aula aula : aulas) {
            objectDbSession.save(aula);
        }
        for (Curso curso : cursos) {
            objectDbSession.save(curso);
        }

        // Confirma la transacción en ObjectDB
        objectDbSession.getTransaction().commit();

        // Cierra las sesiones
        mysqlSession.close();
        objectDbSession.close();

        // Cierra las SessionFactory
        mysqlSessionFactory.close();
        objectDbSessionFactory.close();
    }
    }    