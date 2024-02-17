package com.ieseljust.ad.jsonFiles.jsonfiles;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author pc-raul
 */

public class JsonFiles {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la mejor bbdd de cinematografía");
        System.out.println("Elija una opción:");
        System.out.println("1. Buscar actores");
        System.out.println("2. Buscar películas por actor");
        System.out.println("3. Buscar películas por año");
        System.out.println("4. Buscar directores");
        System.out.println("Ingrese el número de opción: ");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                System.out.print("Ingrese el nombre del actor: ");
                String actorName = scanner.nextLine();
                searchActor(actorName);
                break;
            case "2":
                System.out.print("Ingrese el nombre del actor: ");
                String actorNameForFilms = scanner.nextLine();
                listFilmsByActor(actorNameForFilms);
                break;
            case "3":
                System.out.print("Ingrese el año de la película: ");
                String year = scanner.nextLine();
                listFilms(year);
                break;
            case "4":
                System.out.print("Ingrese el nombre del director: ");
                String directorName = scanner.nextLine();
                listMoviesByDirector(directorName);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void searchActor(String actorName) {
    // Indica la ruta donde quieres buscar actores (por ejemplo, nombres de actores).
    File actorsDir = new File("/home/pc-raul/2DAM/2DAM_Course/JsonFiles/actors/");
    File[] actorFiles = actorsDir.listFiles();

    if (actorFiles != null) {
        for (File file : actorFiles) {
            JSONObject actor = readJSON(file.getAbsolutePath());

            // Aquí puedes especificar el campo en el que deseas buscar (por ejemplo, "name").
            String fieldValue = (String) actor.get("name");

            // Realiza la comparación insensible a las mayúsculas y minúsculas.
            if (fieldValue != null && fieldValue.equalsIgnoreCase(actorName)) {
                System.out.println("Información del actor:");
                System.out.println("Nombre: " + actor.get("name"));
                System.out.println("Nombre de nacimiento: " + actor.get("birthname"));
                System.out.println("Fecha de nacimiento: " + actor.get("birthdate"));
                System.out.println("Lugar de nacimiento: " + actor.get("birthplace"));
                // Puedes mostrar más detalles si es necesario.
                return; // Termina la búsqueda una vez que se encuentra un resultado.
            }
        }
    }

    // Si no se encontró ninguna coincidencia, muestra un mensaje de error.
    System.out.println("No se encontró información para el actor: " + actorName);
}

    private static void listFilmsByActor(String actorName) {
        // Indica la ruta de las películas
        File filmsDir = new File("/home/pc-raul/2DAM/2DAM_Course/JsonFiles/movies");
        File[] filmFiles = filmsDir.listFiles();

        if (filmFiles != null) {
            for (File file : filmFiles) {
                JSONObject film = readJSON(file.getAbsolutePath());
                // Verifica si el actor está en el elenco de la película
                JSONArray cast = (JSONArray) film.get("reparto");
                if (cast.contains(actorName)) {
                    System.out.println("Película en la que actúa " + actorName + ": " + film.get("nombre"));
                }
            }
        }
    }

    private static void listFilms(String year) {
        // Indica la ruta de las películas
        File filmsDir = new File("/home/pc-raul/2DAM/2DAM_Course/JsonFiles/movies");
        File[] filmFiles = filmsDir.listFiles();

        if (filmFiles != null) {
            for (File file : filmFiles) {
                JSONObject film = readJSON(file.getAbsolutePath());
                // Comprueba si el año de la película coincide con el año especificado
                if (film.get("año").toString().equals(year)) {
                    System.out.println("Nombre de la película: " + film.get("nombre"));
                    // Aquí puedes agregar más atributos si es necesario
                }
            }
        }
    }
    
    private static void listMoviesByDirector(String directorName) {
        // Indica la ruta donde quieres buscar directores (por ejemplo, nombres de directores).
        File directorsDir = new File("/home/pc-raul/2DAM/2DAM_Course/JsonFiles/directors");
        File[] directorFiles = directorsDir.listFiles();

        if (directorFiles != null) {
            for (File file : directorFiles) {
                JSONObject director = readJSON(file.getAbsolutePath());

                // Aquí puedes especificar el campo en el que deseas buscar (por ejemplo, "nombre").
                String fieldValue = (String) director.get("nombre");

                // Realiza la comparación para buscar coincidencias.
                if (fieldValue != null && fieldValue.equalsIgnoreCase(directorName)) {
                    System.out.println("Peliculas dirigidas por " + directorName + ":");
                    JSONArray movies = (JSONArray) director.get("peliculas");
                    for (Object movie : movies) {
                        System.out.println(movie);
                    }
                }
            }
        }
    }

    private static JSONObject readJSON(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(fileReader);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}