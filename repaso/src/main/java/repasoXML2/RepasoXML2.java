package repasoXML2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pc-raul
 */
// Ejercicio 1: Crea un programa Java que abra el archivo XML redsocial.xml que mencionaste en la ruta /home/pc_raul/NetBeansProjects/repaso/redsocial.xml utilizando el DOM. Luego, muestra en la consola la información de al menos un usuario, incluyendo su nombre, edad, ciudad, intereses, amigos y publicaciones.
public class RepasoXML2 {
    public static void main(String[] args) {
        try {
            String filePath = "/home/pc-raul/NetBeansProjects/repaso/redsocial.xml";
            Document document = parseXML(filePath);

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n--- Menú ---");
                System.out.println("1. Mostrar información de un usuario");
                System.out.println("2. Crear un nuevo usuario");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        mostrarInformacionUsuario(document);
                        break;
                    case 2:
                        crearNuevoUsuario(document);
                        guardarDocumentoXML(document, filePath);
                        break;
                    case 3:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Función para parsear el archivo XML y obtener el elemento raíz del documento DOM
    private static Document parseXML(String filePath) throws Exception {
        // Crear un DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Crear un DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Parsear el documento XML y obtener el elemento raíz
        return builder.parse(new File(filePath));
    }

    private static void mostrarInformacionUsuario(Document document) {
    NodeList userList = document.getElementsByTagName("Usuario");

    if (userList.getLength() > 0) {
        Node userNode = userList.item(0); // Obtener el primer usuario (cambia el índice si deseas mostrar otro usuario)
        if (userNode.getNodeType() == Node.ELEMENT_NODE) {
            Element userElement = (Element) userNode;

            String nombre = userElement.getElementsByTagName("Nombre").item(0).getTextContent();
            String edad = userElement.getElementsByTagName("Edad").item(0).getTextContent();
            String ciudad = userElement.getElementsByTagName("Ciudad").item(0).getTextContent();

            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Ciudad: " + ciudad);

            // También puedes acceder a otros elementos como Intereses, Amigos y Publicaciones de manera similar
        }
    } else {
        System.out.println("No se encontraron usuarios en el archivo XML.");
    }
}

    private static void crearNuevoUsuario(Document document) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario los detalles del nuevo usuario
        System.out.println("Ingrese los detalles del nuevo usuario:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        String edad = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Intereses (separados por comas): ");
        String intereses = scanner.nextLine();
        System.out.print("Amigos (separados por comas, IDs de otros usuarios): ");
        String amigos = scanner.nextLine();
        System.out.print("Publicación: ");
        String publicacion = scanner.nextLine();

        // Crear un nuevo elemento <Usuario> y agregar sus elementos hijos
        Element nuevoUsuario = document.createElement("Usuario");

        Element nombreElement = document.createElement("Nombre");
        nombreElement.appendChild(document.createTextNode(nombre));

        Element edadElement = document.createElement("Edad");
        edadElement.appendChild(document.createTextNode(edad));

        Element ciudadElement = document.createElement("Ciudad");
        ciudadElement.appendChild(document.createTextNode(ciudad));

        Element interesesElement = document.createElement("Intereses");
        String[] interesesArray = intereses.split(",");
        for (String interes : interesesArray) {
            Element interesElement = document.createElement("Interes");
            interesElement.appendChild(document.createTextNode(interes.trim()));
            interesesElement.appendChild(interesElement);
        }

        Element amigosElement = document.createElement("Amigos");
        String[] amigosArray = amigos.split(",");
        for (String amigo : amigosArray) {
            Element amigoIDElement = document.createElement("AmigoID");
            amigoIDElement.appendChild(document.createTextNode(amigo.trim()));
            amigosElement.appendChild(amigoIDElement);
        }

        Element publicacionesElement = document.createElement("Publicaciones");
        Element publicacionElement = document.createElement("Publicacion");

        Element textoElement = document.createElement("Texto");
        textoElement.appendChild(document.createTextNode(publicacion));

        publicacionElement.appendChild(textoElement);
        publicacionesElement.appendChild(publicacionElement);

        nuevoUsuario.appendChild(nombreElement);
        nuevoUsuario.appendChild(edadElement);
        nuevoUsuario.appendChild(ciudadElement);
        nuevoUsuario.appendChild(interesesElement);
        nuevoUsuario.appendChild(amigosElement);
        nuevoUsuario.appendChild(publicacionesElement);

        document.getDocumentElement().appendChild(nuevoUsuario);
        System.out.println("Nuevo usuario agregado con éxito.");
    }

    // Función para guardar el documento DOM en el archivo XML
    private static void guardarDocumentoXML(Document document, String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);
            System.out.println("Documento XML actualizado y guardado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}