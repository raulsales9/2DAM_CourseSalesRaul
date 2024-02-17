/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReadWriteJSON;

/**
 *
 * @author pc-raul
 */
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public class ReadWriteJSON {
    public static void main(String[] args) {
        try {
            // Lee el archivo JSON
            FileReader reader = new FileReader("data.json");
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonRoot = new JSONObject(tokener);

            // Accede a los datos en el JSON anidado
            String nombre = jsonRoot.getString("nombre");
            System.out.println("Nombre: " + nombre);

            JSONArray numeros = jsonRoot.getJSONArray("numeros");
            System.out.println("Números:");
            for (int i = 0; i < numeros.length(); i++) {
                System.out.println(numeros.getInt(i));
            }

            JSONObject informacion = jsonRoot.getJSONObject("informacion");
            String ciudad = informacion.getString("ciudad");
            String pais = informacion.getString("pais");
            int poblacion = informacion.getInt("poblacion");
            System.out.println("Información:");
            System.out.println("Ciudad: " + ciudad);
            System.out.println("País: " + pais);
            System.out.println("Población: " + poblacion);

            JSONArray personas = jsonRoot.getJSONArray("personas");
            System.out.println("Personas:");
            for (int i = 0; i < personas.length(); i++) {
                JSONObject persona = personas.getJSONObject(i);
                String nombrePersona = persona.getString("nombre");
                int edad = persona.getInt("edad");
                String profesion = persona.getString("profesion");
                System.out.println("Nombre: " + nombrePersona);
                System.out.println("Edad: " + edad);
                System.out.println("Profesión: " + profesion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





