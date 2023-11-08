package com.mycompany.dataManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {
    private Connection dbConnection;

    public DataManager(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void initializeDatabase() throws SQLException {
        // Crea tablas y realiza otras operaciones iniciales aquí
        Statement statement = dbConnection.createStatement();

        // Ejemplo: Crea una tabla si no existe (reemplaza con tu estructura real)
        String createTableSQL = "CREATE TABLE IF NOT EXISTS sample_table (id INT PRIMARY KEY, name VARCHAR(255))";
        statement.execute(createTableSQL);

        // Agrega más tablas o inicialización según sea necesario
    }

    public void insertData(int id, String name) throws SQLException {
        // Realiza la inserción de datos en la base de datos
        String insertSQL = "INSERT INTO sample_table (id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }
    }

    public void updateData(int id, String newName) throws SQLException {
        // Realiza la actualización de datos en la base de datos
        String updateSQL = "UPDATE sample_table SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteData(int id) throws SQLException {
        // Realiza la eliminación de datos en la base de datos
        String deleteSQL = "DELETE FROM sample_table WHERE id = ?";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}