package com.mycompany.jsontodb;
import com.mycompany.conectionManager.ConnectionManager;
import com.mycompany.dataManager.DataManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

public class JSONToDatabase {
    public static void main(String[] args) {
        // Reemplaza con la ruta real del archivo JSON
        String jsonFilePath = "data.json";

        // Inicializa ConnectionManager con tus parámetros de conexión a la base de datos
        ConnectionManager connectionManager = new ConnectionManager("tu_servidor_db", "tu_puerto_db", "tu_usuario_db", "tu_contraseña_db");

        try (Connection dbConnection = connectionManager.connectDBMS()) {
            // Lee y analiza los datos JSON
            String jsonData = readFile(jsonFilePath);
            JSONArray jsonArray = new JSONArray(jsonData);

            // Realiza las operaciones en la base de datos
            DataManager dataManager = new DataManager(dbConnection);
            dataManager.initializeDatabase();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id"); // Ajusta para que coincida con la estructura de tu JSON
                String name = jsonObject.getString("name"); // Ajusta para que coincida con la estructura de tu JSON

                dataManager.insertData(id, name);
            }

            // Realiza actualizaciones y eliminaciones según sea necesario
            dataManager.updateData(1, "NuevoNombre"); // Ejemplo: Actualiza un registro con id 1
            dataManager.deleteData(2); // Ejemplo: Elimina un registro con id 2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
}
