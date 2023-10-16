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
        if (args.length < 1) {
            System.out.println("Uso incorrecto. Debes proporcionar al menos un argumento.");
            return;
        }

        String action = args[0].toLowerCase(); // Convertir a minúsculas para evitar sensibilidad a mayúsculas

        switch (action) {
            case "actores":
                if (args.length > 1) {
                    for (int i = 1; i < args.length; i++) {
                        searchActor(args[i]);
                    }
                } else {
                    System.out.println("Lista de actores:");
                    listActors();
                }
                break;
            case "peliculas":
                if (args.length != 2) {
                    System.out.println("Uso incorrecto. Debes proporcionar un año.");
                    return;
                }
                String year = args[1];
                listFilms(year);
                break;
            case "directores":
                if (args.length > 1) {
                    for (int i = 1; i < args.length; i++) {
                        searchDirector(args[i]);
                    }
                } else {
                    System.out.println("Lista de directores:");
                    listDirectors();
                }
                break;
            default:
                System.out.println("Acción no válida. Las acciones válidas son 'actores', 'peliculas' y 'directores'.");
                break;
        }
    }

    private static void searchActor(String actorName) {
        File actorsDir = new File("actors/");
        File[] actorFiles = actorsDir.listFiles();

        if (actorFiles != null) {
            for (File file : actorFiles) {
                JSONObject actor = readJSON(file);

                String fieldValue = (String) actor.get("name");

                if (fieldValue != null && fieldValue.equalsIgnoreCase(actorName)) {
                    System.out.println("Información del actor:");
                    System.out.println(actor.toJSONString());
                    return;
                }
            }
        }

        System.out.println("No se encontró información para el actor: " + actorName);
    }
    
    private static void searchDirector(String directorName) {
    File directorsDir = new File("directors/");
    File[] directorFiles = directorsDir.listFiles();

    if (directorFiles != null) {
        for (File file : directorFiles) {
            JSONObject director = readJSON(file);

            String fieldValue = (String) director.get("name");

            if (fieldValue != null && fieldValue.equalsIgnoreCase(directorName)) {
                System.out.println("Información del director:");
                System.out.println(director.toJSONString());
                return;
            }
        }
    }

    System.out.println("No se encontró información para el director: " + directorName);
}

    private static void listActors() {
        File actorsDir = new File("actors/");
        File[] actorFiles = actorsDir.listFiles();

        if (actorFiles != null) {
            for (File file : actorFiles) {
                JSONObject actor = readJSON(file);
                String actorName = (String) actor.get("name");
                System.out.println("Actor: " + actorName);
                System.out.println(actor.toJSONString());
            }
        }
    }

    private static void listFilms(String year) {
        File filmsDir = new File("movies/");
        File[] filmFiles = filmsDir.listFiles();

        if (filmFiles != null) {
            for (File file : filmFiles) {
                JSONObject film = readJSON(file);

                String fieldValue = (String) film.get("year");

                if (fieldValue != null && fieldValue.equals(year)) {
                    System.out.println("Información de la película:");
                    System.out.println(film.toJSONString());
                }
            }
        }
    }

    private static void listDirectors() {
        File directorsDir = new File("directors/");
        File[] directorFiles = directorsDir.listFiles();

        if (directorFiles != null) {
            for (File file : directorFiles) {
                JSONObject director = readJSON(file);

                String fieldValue = (String) director.get("name");

                if (fieldValue != null) {
                    System.out.println("Información del director:");
                    System.out.println(director.toJSONString());
                }
            }
        }
    }

    private static JSONObject readJSON(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(fileReader);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}