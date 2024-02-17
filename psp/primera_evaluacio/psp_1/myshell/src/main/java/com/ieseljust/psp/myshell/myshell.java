package com.ieseljust.psp.myshell;

// paquets a utilitzar per myshell
import org.jline.reader.*;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

// paquets per a controlar l'entrada e ixida
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class myshell {

    public static void main(String[] args) {
        try {
            // Construim una terminal
            Terminal terminal = TerminalBuilder.builder().build();
            // instanciem per a llegir la terminal 
            LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

            // shell ok
            System.out.println("MyShell running");
            while (true) {
                // defineix el prompt amb mensaje que retorna la shell al iniciar
                String prompt = "# MyShell> ";
                
                //
                System.out.print(prompt);   
                String line = reader.readLine(prompt);

                // llegim la lines de l'usuari i inidiquem l'eixida del bucle
                if (line.equalsIgnoreCase("quit")) {
                    break;
                }
                // crida per executar comandos
                executeCommand(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            // bucle per a recorrer les linies
            while ((line = reader.readLine()) != null) {
                // establim color de eixa, verd
                System.out.println("\u001B[32m");
                System.out.println(line);

                //establim color roig
                System.out.print("\u001B[0m");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                // imprimix mensatge d'error
                System.err.println("Error: El comando salió con el código de salida " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
