/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryToJSON;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author pc-raul
 */
public class BinaryToJSON {
    public static void main(String[] args) {
        try {
            // Leer datos binarios desde un archivo
            try (FileInputStream fis = new FileInputStream("data.bin");
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                String jsonString = (String) ois.readObject();

                // Convertir la cadena JSON en un objeto JSON
                JSONObject jsonObject = new JSONObject(jsonString);

                // Acceder a los datos JSON
                String nombre = jsonObject.getString("nombre");
                int[] numeros = jsonObject.getJSONArray("numeros").toList()
                        .stream()
                        .map(o -> (int) o)
                        .mapToInt(Integer::intValue)
                        .toArray();

                JSONObject informacion = jsonObject.getJSONObject("informacion");
                String ciudad = informacion.getString("ciudad");
                String pais = informacion.getString("pais");
                int poblacion = informacion.getInt("poblacion");

                JSONArray personas = jsonObject.getJSONArray("personas");
                for (int i = 0; i < personas.length(); i++) {
                    JSONObject persona = personas.getJSONObject(i);
                    String nombrePersona = persona.getString("nombre");
                    int edad = persona.getInt("edad");
                    String profesion = persona.getString("profesion");

                    System.out.println("Nombre: " + nombrePersona);
                    System.out.println("Edad: " + edad);
                    System.out.println("Profesión: " + profesion);
                    System.out.println();
                }

                // Puedes acceder a los datos JSON como se muestra arriba
                System.out.println("Nombre: " + nombre);
                System.out.println("Números: " + java.util.Arrays.toString(numeros));
                System.out.println("Ciudad: " + ciudad);
                System.out.println("País: " + pais);
                System.out.println("Población: " + poblacion);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
