
package com.ieseljust.ad_23_24.ex7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author samuel
 */
class fileXML {

    public Document OpenXML(String name) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {

        // Create an instance of DocumentBuilderFactory 
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document dom = null;

        try {
//            File newfile = new File(name);
            dom = dBuilder.parse(name);

        } catch (IOException e) {

        }
        return dom;
    }

    public void printModules(Element root) {
        NodeList modules = root.getElementsByTagName("module");
        for (int i = 0; i < modules.getLength(); i++) {
            Element e = (Element) modules.item(i);
//            System.out.println(e.getNodeName() + " " + (i + 1));

            System.out.println("Nom: " + e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
            System.out.println("Hores: " + e.getElementsByTagName("hours").item(0).getFirstChild().getNodeValue());
//            System.out.println("Qualificació: " + e.getElementsByTagName("qualificacio").item(0).getFirstChild().getNodeValue());
            System.out.println("Time: " + e.getElementsByTagName("time").item(0).getFirstChild().getNodeValue());
        }
    }

    public void writeModules(File file) throws SAXException, ParserConfigurationException,
            TransformerConfigurationException, FileNotFoundException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        // root elements
        Document doc = dBuilder.newDocument();
        try {

            Element rootElement = doc.createElement("course");
            doc.appendChild(rootElement);

            Element module = doc.createElement("module");

            Element name = doc.createElement("name");
            Element hours = doc.createElement("hours");
            Element currentTime = doc.createElement("time");

            name.appendChild(doc.createTextNode("Accés a dades"));
            hours.appendChild(doc.createTextNode(Integer.toString(8)));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            currentTime.appendChild(doc.createTextNode(now.toString()));

            module.appendChild(name);
            module.appendChild(hours);
            module.appendChild(currentTime);

            rootElement.appendChild(module);
        } catch (Exception e) {
            System.out.println(e);
        }

        Transformer trans = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(file));
        trans.transform(source, result);

    }

}

public class modulesXML {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException, TransformerException {
        fileXML dom = new fileXML();
        
        if (args[0].equalsIgnoreCase("read")) {
            Document doc = dom.OpenXML(args[1]);
            dom.printModules(doc.getDocumentElement());
        }
        if (args[0].equalsIgnoreCase("write")) {
            File file = new File(args[1]);
            if(!file.exists()) {
                file.createNewFile();
            }
//            Document doc = dom.OpenXML(args[1]);
            dom.writeModules(file);
        }
    }

}