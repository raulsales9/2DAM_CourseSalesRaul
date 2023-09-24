package com.ieseljust.psp.myshell;

import org.jline.reader.*;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class myshell {

    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.builder().build();
            LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

            System.out.println("Shell running");
            while (true) {
                String prompt = "# MyShell> ";
                String line = reader.readLine(prompt);

                if (line.equalsIgnoreCase("quit")) {
                    break;
                }

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
            while ((line = reader.readLine()) != null) {
                System.out.println("\u001B[32m");
                System.out.println(line);
                System.out.print("\u001B[0m");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error: El comando salió con el código de salida " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
