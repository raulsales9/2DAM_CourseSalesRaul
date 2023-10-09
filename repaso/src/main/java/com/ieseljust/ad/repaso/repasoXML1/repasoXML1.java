/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.ad.repaso.repasoXML1;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author pc-raul
 */
public class repasoXML1 {
    // Crear HTML con datos de concesionario
    public static void main(String[] args){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            
            Document document = implementation.createDocument(null, "datos", null);
            document.setXmlVersion("1.0");
            
            Element coches = document.createElement("coches");
            Element coche = document.createElement("coche");
            
            Element matricula = document.createElement("matricula");
            Text textMatricula = document.createTextNode("1111AAA");
            matricula.appendChild(textMatricula);
            coche.appendChild(matricula);
            
            Element marca = document.createElement("marca");
            Text textmarca = document.createTextNode("audi");
            marca.appendChild(textmarca);
            coche.appendChild(marca);
                    
            Element precio = document.createElement("precio");
            Text textprecio = document.createTextNode("30.000€");
            precio.appendChild(textprecio);
            coche.appendChild(precio);
             
            
            coches.appendChild(coche);
            document.getDocumentElement().appendChild(coches);
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("datos.xml"));
            
            Transformer transformer  = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }catch(ParserConfigurationException | TransformerException ex){
            System.out.println("");
        }
    }
    
}
