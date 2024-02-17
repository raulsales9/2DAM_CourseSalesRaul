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
import org.hibernate.Transaction;

import org.raul.conversor.entities_mysql.Horario;
import org.raul.conversor.entities_mysql.Profesor;
import postgre_obj.utils.HibernateUtil;



public class Main {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Obtener datos de MySQL
            List<Estudiante> estudiantes = session.createQuery("from Estudiante").list();
            List<Profesor> profesores = session.createQuery("from Profesor").list();
            List<Curso> cursos = session.createQuery("from Curso").list();
            List<Asignatura> asignaturas = session.createQuery("from Asignatura").list();
            List<Aula> aulas = session.createQuery("from Aula").list();
            List<Horario> horarios = session.createQuery("from Horario").list();

            session.close();

            // Persistir datos en ObjectDB
            persistirDatosEnODB(estudiantes);
            persistirDatosEnODB(profesores);
            persistirDatosEnODB(cursos);
            persistirDatosEnODB(asignaturas);
            persistirDatosEnODB(aulas);
            persistirDatosEnODB(horarios);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void persistirDatosEnODB(List<?> entidades) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // Configuración directa de EntityManagerFactory para ObjectDB
            emf = Persistence.createEntityManagerFactory("objectdb:./database.odb");

            em = emf.createEntityManager();
            em.getTransaction().begin();

            for (Object entidad : entidades) {
                em.persist(entidad);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}