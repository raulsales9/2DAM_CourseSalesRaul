
package XMLToCSV;

import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author pc-raul
 */

public class XMLtoCSV {

    public static void main(String[] args) {
        try {
            String xmlFilePath = "/home/pc-raul/NetBeansProjects/repaso/redsocial.xml";
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document document = dBuilder.parse(xmlFilePath);
            document.getDocumentElement().normalize();

            FileWriter csvWriter = new FileWriter("redsocial.csv");
            csvWriter.append("ID,Nombre,Edad,Ciudad,Intereses,Amigos,Publicaciones\n");

            NodeList userList = document.getElementsByTagName("Usuario");

            for (int i = 0; i < userList.getLength(); i++) {
                Element usuario = (Element) userList.item(i);

                String id = obtenerValor(usuario, "ID");
                String nombre = obtenerValor(usuario, "Nombre");
                String edad = obtenerValor(usuario, "Edad");
                String ciudad = obtenerValor(usuario, "Ciudad");
                String intereses = obtenerContenidoLista(usuario.getElementsByTagName("Interes"));
                String amigos = obtenerContenidoLista(usuario.getElementsByTagName("AmigoID"));
                String publicaciones = obtenerPublicaciones(usuario);

                csvWriter.append(id + "," + nombre + "," + edad + "," + ciudad + "," + intereses + "," + amigos + "," + publicaciones + "\n");
            }

            csvWriter.close();
            System.out.println("Conversión a CSV completada. Archivo redsocial.csv creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String obtenerValor(Element element, String tagName) {
        if (element.getElementsByTagName(tagName).getLength() > 0) {
            return element.getElementsByTagName(tagName).item(0).getTextContent();
        } else {
            return "";
        }
    }

    private static String obtenerContenidoLista(NodeList lista) {
        StringBuilder contenido = new StringBuilder();
        for (int i = 0; i < lista.getLength(); i++) {
            Element elemento = (Element) lista.item(i);
            contenido.append(elemento.getTextContent());
            if (i < lista.getLength() - 1) {
                contenido.append(", ");
            }
        }
        return contenido.toString();
    }

    private static String obtenerPublicaciones(Element usuario) {
        NodeList publicaciones = usuario.getElementsByTagName("Publicacion");
        StringBuilder publicacionesString = new StringBuilder();

        for (int i = 0; i < publicaciones.getLength(); i++) {
            Element publicacion = (Element) publicaciones.item(i);
            String id = obtenerValor(publicacion, "ID");
            String texto = obtenerValor(publicacion, "Texto");

            if (!id.isEmpty() && !texto.isEmpty()) {
                publicacionesString.append(id).append(": ").append(texto);
                if (i < publicaciones.getLength() - 1) {
                    publicacionesString.append(", ");
                }
            }
        }

        return publicacionesString.toString();
    }
}