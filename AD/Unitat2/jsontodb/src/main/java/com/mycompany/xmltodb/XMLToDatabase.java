package com.mycompany.xmltodb;
import com.mycompany.conectionManager.ConnectionManager;
import com.mycompany.dataManager.DataManager;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLToDatabase {
    public static void main(String[] args) {
        // Reemplaza con la ruta real del archivo XML
        String xmlFilePath = "data.xml";

        // Inicializa ConnectionManager con tus parámetros de conexión a la base de datos
        ConnectionManager connectionManager = new ConnectionManager("tu_servidor_db", "tu_puerto_db", "tu_usuario_db", "tu_contraseña_db");

        try (Connection dbConnection = connectionManager.connectDBMS()) {
            // Parsea el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(xmlFilePath));

            // Obtiene la lista de registros
            NodeList recordNodes = doc.getElementsByTagName("record");

            // Realiza las operaciones en la base de datos
            DataManager dataManager = new DataManager(dbConnection);
            dataManager.initializeDatabase();

            for (int i = 0; i < recordNodes.getLength(); i++) {
                Element recordElement = (Element) recordNodes.item(i);
                int id = Integer.parseInt(recordElement.getElementsByTagName("id").item(0).getTextContent());
                String name = recordElement.getElementsByTagName("name").item(0).getTextContent();

                dataManager.insertData(id, name);
            }

            // Realiza actualizaciones y eliminaciones según sea necesario
            dataManager.updateData(1, "NuevoNombre"); // Ejemplo: Actualiza un registro con id 1
            dataManager.deleteData(2); // Ejemplo: Elimina un registro con id 2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
