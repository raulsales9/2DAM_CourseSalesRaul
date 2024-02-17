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

public class CSVExporter {
    public static void exportTableToCSV(Connection connection, String tableName, String csvFilePath) {
        try (FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {

            // Consulta SQL para seleccionar todos los registros de la tabla
            String query = "SELECT * FROM " + tableName;
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Obtener los nombres de las columnas
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                csvPrinter.print(resultSet.getMetaData().getColumnName(i));
            }
            csvPrinter.println();

            // Escribir los datos de la tabla en el archivo CSV
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    csvPrinter.print(resultSet.getString(i));
                }
                csvPrinter.println();
            }

            System.out.println("Exportación exitosa a " + csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error en la consulta SQL: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Reemplaza con tus propios valores de conexión, tabla y ruta de archivo CSV
        String jdbcUrl = "jdbc:mysql://localhost:3306/tu_basededatos";
        String username = "tu_usuario";
        String password = "tu_contraseña";
        String tableName = "tu_tabla";
        String csvFilePath = "ruta/del/archivo.csv";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            exportTableToCSV(connection, tableName, csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
        }
    }
}

public class CSVExporter {
    public static void exportTableToCSV(Connection connection, String tableName, String csvFilePath) {
        try (FileWriter fileWriter = new FileWriter(csvFilePath);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Escribir los nombres de las columnas en el archivo CSV
            for (int i = 1; i <= columnCount; i++) {
                fileWriter.append(metaData.getColumnName(i));
                if (i < columnCount) {
                    fileWriter.append(",");
                }
            }
            fileWriter.append("\n");

            // Escribir los datos de la tabla en el archivo CSV
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    fileWriter.append(resultSet.getString(i));
                    if (i < columnCount) {
                        fileWriter.append(",");
                    }
                }
                fileWriter.append("\n");
            }

            System.out.println("Exportación exitosa a " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error en la consulta SQL: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Reemplaza con tus propios valores de conexión, tabla y ruta de archivo CSV
        String jdbcUrl = "jdbc:mysql://localhost:3306/tu_basededatos";
        String username = "tu_usuario";
        String password = "tu_contraseña";
        String tableName = "tu_tabla";
        String csvFilePath = "ruta/del/archivo.csv";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            exportTableToCSV(connection, tableName, csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
        }
    }
}


}