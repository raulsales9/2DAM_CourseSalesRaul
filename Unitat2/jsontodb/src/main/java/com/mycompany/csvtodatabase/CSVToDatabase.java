package com.mycompany.csvtodatabase;
import com.mycompany.conectionManager.ConnectionManager;
import com.mycompany.dataManager.DataManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CSVToDatabase {
    public static void main(String[] args) {
        // Reemplaza con la ruta real del archivo CSV
        String csvFilePath = "data.csv";

        // Inicializa ConnectionManager con tus parámetros de conexión a la base de datos
        ConnectionManager connectionManager = new ConnectionManager("tu_servidor_db", "tu_puerto_db", "tu_usuario_db", "tu_contraseña_db");

        try (Connection dbConnection = connectionManager.connectDBMS()) {
            // Lee y analiza los datos CSV
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;

            // Salta la primera línea si es un encabezado
            reader.readLine();

            // Realiza las operaciones en la base de datos
            DataManager dataManager = new DataManager(dbConnection);
            dataManager.initializeDatabase();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2) {
                    int id = Integer.parseInt(values[0].trim()); // Ajusta para que coincida con la estructura de tu CSV
                    String name = values[1].trim(); // Ajusta para que coincida con la estructura de tu CSV

                    dataManager.insertData(id, name);
                }
            }

            // Realiza actualizaciones y eliminaciones según sea necesario
            dataManager.updateData(1, "NuevoNombre"); // Ejemplo: Actualiza un registro con id 1
            dataManager.deleteData(2); // Ejemplo: Elimina un registro con id 2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
