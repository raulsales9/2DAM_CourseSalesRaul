package com.ieseljust.ad.xmlfiles.csvfiles;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author pc-raul
 */
public class CsvFiles {
    
    
    
    private void showCSV(String[] filename, boolean isHeader, String separator) throw IOException {
        
        List<String> records = Files.readAllLines(Paths.get(filename));
        // string = "hurricanes.csv"
        
        if(isHeader){
            String line = records.get(0);
            records.remove(0);
            String[] headers = line.split(separator);
            int i = 0;
            for(String header : headers){
                System.out.printf("%15s", header);
                i += 15;
            }
            System.out.println("");
            imprimirCarecterRepe("-", i + headers.length +1);
            
            for(String record : records){
                String[] values = record.split(separator);
                for(String value : values){
                    System.out.println("");
                }
            }
        }
    }
    private void addlinestocsv(String filename) throw IOException{
        //FileWriter output = 
        try{
            
        }catch(){
            
        }
    }
}
