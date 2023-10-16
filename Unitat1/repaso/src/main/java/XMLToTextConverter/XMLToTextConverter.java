/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XMLToTextConverter;

import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author pc-raul
 */
public class XMLToTextConverter {
    public static void main(String[] args) {
        String xmlFilePath = "/home/pc-raul/NetBeansProjects/repaso/redsocial.xml";
        String txtFilePath = "/home/pc-raul/NetBeansProjects/repaso/redsocial.txt";

        try {
            // Crear un DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document document = builder.parse(new File(xmlFilePath));

            // Crear un FileWriter para escribir en el archivo de texto
            FileWriter writer = new FileWriter(txtFilePath);

            // Obtener la lista de usuarios
            NodeList userList = document.getElementsByTagName("Usuario");

            // Recorrer la lista de usuarios y escribir los datos en el archivo de texto
            for (int i = 0; i < userList.getLength(); i++) {
                Element userElement = (Element) userList.item(i);
                Node idNode = userElement.getElementsByTagName("ID").item(0);

                // Verificar si el elemento <ID> existe
                if (idNode != null) {
                    String id = idNode.getTextContent();
                    String nombre = userElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String edad = userElement.getElementsByTagName("Edad").item(0).getTextContent();
                    String ciudad = userElement.getElementsByTagName("Ciudad").item(0).getTextContent();

                    // Escribir los datos en el archivo de texto
                    writer.write("ID: " + id + "\n");
                    writer.write("Nombre: " + nombre + "\n");
                    writer.write("Edad: " + edad + "\n");
                    writer.write("Ciudad: " + ciudad + "\n");
                    writer.write("\n"); // Separador entre usuarios
                }
            }

            // Cerrar el FileWriter
            writer.close();

            System.out.println("Conversión de XML a texto completada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




