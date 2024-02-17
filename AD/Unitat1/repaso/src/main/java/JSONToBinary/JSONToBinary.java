/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONToBinary;

/**
 *
 * @author pc-raul
 */
import org.json.JSONObject;

import java.io.*;
import org.json.JSONArray;

public class JSONToBinary {
    public static void main(String[] args) {
        try {
            // Crear un objeto JSON
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nombre", "Ejemplo de JSON a binario");
            jsonObject.put("numeros", new int[]{1, 2, 3, 4, 5});
            jsonObject.put("informacion", new JSONObject()
                    .put("ciudad", "Nueva York")
                    .put("pais", "Estados Unidos")
                    .put("poblacion", 8419600));

            JSONArray personas = new JSONArray();
            personas.put(new JSONObject()
                    .put("nombre", "Juan")
                    .put("edad", 30)
                    .put("profesion", "Ingeniero"));
            personas.put(new JSONObject()
                    .put("nombre", "María")
                    .put("edad", 28)
                    .put("profesion", "Doctor"));
            jsonObject.put("personas", personas);

            // Convertir el objeto JSON a una cadena JSON
            String jsonString = jsonObject.toString();

            // Serializar la cadena JSON a un archivo binario
            try (FileOutputStream fos = new FileOutputStream("data.bin");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(jsonString);
            }

            System.out.println("JSON convertido a archivo binario (data.bin).");

            // Para leer el archivo binario y convertirlo nuevamente a un objeto JSON
            try (FileInputStream fis = new FileInputStream("data.bin");
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                String jsonStr = (String) ois.readObject();
                JSONObject jsonResult = new JSONObject(jsonStr);
                System.out.println(jsonResult);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}