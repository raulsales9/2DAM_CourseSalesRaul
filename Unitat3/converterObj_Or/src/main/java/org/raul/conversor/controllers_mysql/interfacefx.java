/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.raul.conversor.controllers_mysql;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Session;
import org.raul.conversor.entities_mysql.Asignatura;
import postgre_obj.utils.HibernateUtil;

/**
 *
 * @author rauls
 */
public class interfacefx {
      @FXML
    private TableView<Asignatura> tablaAsignaturas;
    @FXML
    private TableColumn<Asignatura, String> columnaId;
    @FXML
    private TableColumn<Asignatura, String> columnaNombre;
    @FXML
    private Button botonCrear;
    @FXML
    private Button botonActualizar;
    @FXML
    private Button botonBorrar;

    @FXML
    public void initialize() {
        // Aquí puedes inicializar tu controlador y cargar los datos de las asignaturas en la tabla
        cargarAsignaturas();
    }

    private void cargarAsignaturas() {
        // Aquí puedes obtener una sesión de Hibernate y cargar las asignaturas en la tabla
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Asignatura> asignaturas = session.createQuery("from Asignatura", Asignatura.class).list();
        tablaAsignaturas.getItems().setAll(asignaturas);
        session.close();
    }

    @FXML
    private void crearAsignatura() {
        // Aquí puedes implementar la lógica para crear una nueva asignatura
    }

    @FXML
    private void actualizarAsignatura() {
        // Aquí puedes implementar la lógica para actualizar una asignatura existente
    }

    @FXML
    private void borrarAsignatura() {
        // Aquí puedes implementar la lógica para borrar una asignatura existente
    }
}
