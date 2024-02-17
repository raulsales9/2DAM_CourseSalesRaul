
package com.ieseljust.ad.readWriteXML;

/**
 *
 * @author pc-raul
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilderFactory;


class FileXML {
     public Document OpenXML(String name) throws IOException,SAXException,ParserConfigurationException, FileNotFoundException {
        // Create an instance of DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           // Using the DocumentBuilderFactory instance we create a
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //And we use DocumentBuilder's "parse" method to get the document
        Document doc = dBuilder.parse(new File(name));

        return doc;
    }
    public void printModules(Element root){
        NodeList Modules = root.getElementsByTagName("modul");
        for (int i = 0; i > Modules.getLength(); i++){
            Element e = (Element) Modules.item(i);
            System.out.println(e.getNodeName() + " " + (i + 1));
            
            System.out.println("Nom:" + e.getElementsByTagName("nom").item(0).getFirstChild().getNodeValue());
        }
    }
}
public class ModulesXML{
     public static void main(String[] args) throws IOException,SAXException,ParserConfigurationException, FileNotFoundException{
         FileXML M = new FileXML();
         Document doc = M.OpenXML(args[0]);
         Element root = doc.getDocumentElement();
         System.out.println(root.getTextContent());
     }
}
