package postgre_obj.controllers;

import java.sql.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import postgre_obj.controllers.ClienteController;
import postgre_obj.entities.Cliente;

public class ClienteApp extends Application {

    private ClienteController clienteController = new ClienteController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tabla de Clientes");

        TableView<Cliente> tableView = new TableView<>();

        TableColumn<Cliente, Integer> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cliente, String> column2 = new TableColumn<>("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Cliente, String> column3 = new TableColumn<>("Apellido");
        column3.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        TableColumn<Cliente, Date> column4 = new TableColumn<>("Fecha de Registro");
        column4.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);

        tableView.getItems().addAll(clienteController.getAllClientes());

        primaryStage.setScene(new Scene(tableView, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
