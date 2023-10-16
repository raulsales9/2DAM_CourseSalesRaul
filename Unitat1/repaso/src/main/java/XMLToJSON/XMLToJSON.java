
package XMLToJSON;

/**
 *
 * @author pc-raul
 */
import java.io.FileWriter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLToJSON {

    public static void main(String[] args) {
        try {
            // Ruta al archivo XML
            String xmlFilePath = "/home/pc-raul/NetBeansProjects/repaso/redsocial.xml";

            // Crear un objeto DocumentBuilder para analizar el XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analizar el archivo XML
            Document document = dBuilder.parse(xmlFilePath);
            document.getDocumentElement().normalize();

            // Crear un objeto JSON
            JSONObject jsonRoot = new JSONObject();

            // Crear una lista de objetos JSON para los usuarios
            JSONArray jsonUsers = new JSONArray();

            // Obtener la lista de usuarios del archivo XML
            NodeList userList = document.getElementsByTagName("Usuario");

            // Iterar a través de los usuarios y crear objetos JSON para cada uno
            for (int i = 0; i < userList.getLength(); i++) {
                Element userElement = (Element) userList.item(i);

                JSONObject jsonUser = new JSONObject();
                jsonUser.put("ID", safeGetTextContent(userElement, "ID"));
                jsonUser.put("Nombre", safeGetTextContent(userElement, "Nombre"));
                jsonUser.put("Edad", safeGetTextContent(userElement, "Edad"));
                jsonUser.put("Ciudad", safeGetTextContent(userElement, "Ciudad"));

                // Procesar los intereses del usuario
                JSONArray jsonIntereses = new JSONArray();
                NodeList interesesList = userElement.getElementsByTagName("Interes");
                for (int j = 0; j < interesesList.getLength(); j++) {
                    jsonIntereses.put(interesesList.item(j).getTextContent());
                }
                jsonUser.put("Intereses", jsonIntereses);

                // Procesar los amigos del usuario
                JSONArray jsonAmigos = new JSONArray();
                NodeList amigosList = userElement.getElementsByTagName("AmigoID");
                for (int j = 0; j < amigosList.getLength(); j++) {
                    jsonAmigos.put(amigosList.item(j).getTextContent());
                }
                jsonUser.put("Amigos", jsonAmigos);

                // Procesar las publicaciones del usuario
                JSONArray jsonPublicaciones = new JSONArray();
                NodeList publicacionesList = userElement.getElementsByTagName("Publicacion");
                for (int j = 0; j < publicacionesList.getLength(); j++) {
                    Element publicacionElement = (Element) publicacionesList.item(j);
                    JSONObject jsonPublicacion = new JSONObject();
                    jsonPublicacion.put("ID", safeGetTextContent(publicacionElement, "ID"));
                    jsonPublicacion.put("Texto", safeGetTextContent(publicacionElement, "Texto"));
                    jsonPublicacion.put("Fecha", safeGetTextContent(publicacionElement, "Fecha"));
                    jsonPublicaciones.put(jsonPublicacion);
                }
                jsonUser.put("Publicaciones", jsonPublicaciones);

                // Agregar el usuario a la lista de usuarios
                jsonUsers.put(jsonUser);
            }

            // Agregar la lista de usuarios al objeto JSON raíz
            jsonRoot.put("Usuarios", jsonUsers);

            // Escribir el objeto JSON en un archivo JSON
            try (FileWriter jsonWriter = new FileWriter("redsocial.json")) {
                jsonWriter.write(jsonRoot.toString(4)); // El '4' indica la cantidad de espacios de sangría para la salida JSON
            }

            System.out.println("Conversión a JSON completada. Archivo redsocial.json creado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String safeGetTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}