import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 *
 * @author pc-raul
 */

/*
clase para crear, imprimir, y abrir un archivo XML 
*/

class FileXML {
    // Parseamos el xml
    public Document openXML(String name) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document dom = null;

        try {
            dom = dBuilder.parse(name);
        } catch (IOException e) {
            System.err.println("Error de lectura del archivo XML: " + e.getMessage());
        }
        return dom;
    }

    //metodo para imprimir modulos de un xml
    public void printModules(Element root) {
        NodeList modules = root.getElementsByTagName("module");
        for (int i = 0; i < modules.getLength(); i++) {
            Element e = (Element) modules.item(i);
            System.out.println("Nom: " + getElementValue(e, "name"));
            System.out.println("Hores: " + getElementValue(e, "hours"));
            System.out.println("Time: " + getElementValue(e, "time"));
        }
    }

    // metodo para nuevo xml
    public void writeModules(File file) throws SAXException, ParserConfigurationException,
            TransformerConfigurationException, FileNotFoundException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("course");
        doc.appendChild(rootElement);

        Element module = doc.createElement("module");
        module.appendChild(createElement(doc, "name", "Accés a dades"));
        module.appendChild(createElement(doc, "hours", "8"));
        module.appendChild(createElement(doc, "time", getCurrentTime()));

        rootElement.appendChild(module);

        Transformer trans = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(file));
        trans.transform(source, result);
    }

    //Metodo para obtener el valor de un XML
    private String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return "";
        }
    }

    //Metodo para crear nuevos XML
    private Element createElement(Document doc, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        return element;
    }
    
    //para recoger la fecha de creación
    private String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    

}


