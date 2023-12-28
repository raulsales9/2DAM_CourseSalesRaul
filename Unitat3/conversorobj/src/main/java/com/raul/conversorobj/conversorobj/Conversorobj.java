
package com.raul.conversorobj.conversorobj;

/**
 *
 * @author rauls
 */
import com.raul.conversorobj.conversorobj.entities_mysql.Asignatura;
import com.raul.conversorobj.conversorobj.entities_mysql.Aula;
import com.raul.conversorobj.conversorobj.entities_mysql.Curso;
import com.raul.conversorobj.conversorobj.entities_mysql.Estudiante;
import com.raul.conversorobj.conversorobj.entities_mysql.Horario;
import com.raul.conversorobj.conversorobj.entities_obj.Estudiantes;
import com.raul.conversorobj.conversorobj.entities_obj.Profesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import com.raul.conversorobj.conversorobj.utils.HibernateUtil;



public class Conversorobj {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
            emf = Persistence.createEntityManagerFactory("objectdb:database.odb");
            em = emf.createEntityManager();

            // Recuperar datos de MySQL
            hibernateSession.beginTransaction();

            List<Estudiante> estudiantes = hibernateSession.createQuery("from Estudiante").list();
            List<Profesor> profesores = hibernateSession.createQuery("from Profesor").list();
            List<Curso> cursos = hibernateSession.createQuery("from Curso").list();
            List<Asignatura> asignaturas = hibernateSession.createQuery("from Asignatura").list();
            List<Aula> aulas = hibernateSession.createQuery("from Aula").list();
            List<Horario> horarios = hibernateSession.createQuery("from Horario").list();

            // Datos de ejemplo para probar la actualización en ObjectDB
            Estudiante estudianteEjemplo = new Estudiante();
            estudianteEjemplo.setNombre("Ejemplo Estudiante1");
            estudianteEjemplo.setApellido("Ejemplo Apellido");
            estudianteEjemplo.setEmail("raul@raul.com");

            hibernateSession.save(estudianteEjemplo);
            hibernateSession.getTransaction().commit();

            // Importar datos de MySQL a ObjectDB
            persistirDatosEnODB(estudiantes, hibernateSession, em);
            persistirDatosEnODB(profesores, hibernateSession, em);
            persistirDatosEnODB(cursos, hibernateSession, em);
            persistirDatosEnODB(asignaturas, hibernateSession, em);
            persistirDatosEnODB(aulas, hibernateSession, em);
            persistirDatosEnODB(horarios, hibernateSession, em);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

    private static void persistirDatosEnODB(List<?> entidades, Session hibernateSession, EntityManager objectdbEm) {
        objectdbEm.getTransaction().begin();

        for (Object entidad : entidades) {
            if (entidad instanceof Estudiante) {
                // Convertir Estudiante a Estudiantes antes de persistir en ObjectDB
                Estudiante estudianteRelacional = (Estudiante) entidad;

                Estudiantes estudianteObjeto = new Estudiantes();
                estudianteObjeto.setNombre(estudianteRelacional.getNombre());
                estudianteObjeto.setApellido(estudianteRelacional.getApellido());
                estudianteObjeto.setEmail(estudianteRelacional.getEmail());

                objectdbEm.persist(estudianteObjeto);
            } else {
                // Para otras entidades, simplemente persistir
                objectdbEm.persist(entidad);
            }
        }

        objectdbEm.getTransaction().commit();
    }
}