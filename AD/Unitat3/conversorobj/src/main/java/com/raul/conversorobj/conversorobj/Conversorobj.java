
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Conversorobj extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de Datos");

        Button convertButton = new Button("Convertir Datos a ObjectDB");
        Button crudButton = new Button("Operaciones CRUD");

        convertButton.setOnAction(e -> convertirDatosObjectDB());
        crudButton.setOnAction(e -> abrirVentanaCRUD());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(convertButton, crudButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void convertirDatosObjectDB() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try (var hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
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

            mostrarAlerta("Conversión completada", "Los datos se han convertido correctamente a ObjectDB.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Err");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

    private void persistirDatosEnODB(List<?> entidades, Session hibernateSession, EntityManager objectdbEm) {
        objectdbEm.getTransaction().begin();

        for (Object entidad : entidades) {
            objectdbEm.persist(entidad);
        }

        objectdbEm.getTransaction().commit();
    }

    private void abrirVentanaCRUD() {
        mostrarAlerta("alerta que no puedo poner porque no entiendo javafx","error");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
