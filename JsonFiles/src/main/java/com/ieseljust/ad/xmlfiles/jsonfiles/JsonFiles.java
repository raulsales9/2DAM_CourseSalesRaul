
package com.ieseljust.ad.xmlfiles.jsonfiles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author pc-raul
 */
public class JsonFiles {

    public static void main(String[] args) {
        //Trobem el ficher json al equip
       
        
    }
    
    private void JSONReader()throws Exception {
         FileReader file = new FileReader("/home/pc-raul/NetBeansProjects/JsonFiles");
    }
    
    private JSONObject ReadJSON(String filename){
        try{
            FileReader file = new FileReader(filename);
            String mysjson = "";
            int i;
            while((i=file.read()) != -1){
                mysjson = mysjson+((char) i);
            } 
            
            file.close();
            return (new JSONObject(mysjson));
        }catch(IOException e){
            println(e)
        }
    }
    
    private void ShowJSON(){
        JSONArray jsa=json.getJSONArray("curs");
        
        for(int i = 0; i < jsa.length(); i++){
            System.out.println("Modul: "+ modul.get("nom"));
            System.out.println("Hores: "+ modul.get("hores"));
            System.out.println("Nota: "+modul.get("nota"));
        }
    }
   
}
