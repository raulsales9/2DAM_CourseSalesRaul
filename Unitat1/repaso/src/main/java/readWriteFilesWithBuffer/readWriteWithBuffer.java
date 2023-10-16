package readWriteFilesWithBuffer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

/**
 *
 * @author pc-raul
 */
public class readWriteWithBuffer {

    public static void main(String[] args) {
        escribirArchivo archivo = new escribirArchivo();
        archivo.escribirArchivo();

        leerFichero lector = new leerFichero();
        lector.leerFichero();
    }

    static class leerFichero {

        public void leerFichero() {
            String filePath = "/home/pc-raul/NetBeansProjects/repaso/escribirarchivo.txt";
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("El archivo no existe.");
                return; // Retorna o realiza alguna acción de manejo de error.
            }

            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(fileReader);
                String linea;
                while ((linea = buffer.readLine()) != null) {
                    System.out.println(linea);
                }
                buffer.close(); // Es importante cerrar el BufferedReader al final.
            } catch (IOException e) {
                System.out.println("Error al leer el archivo.");
                e.printStackTrace();
            }
        }
    }

    static class escribirArchivo {

        public void escribirArchivo() {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("/home/pc-raul/NetBeansProjects/repaso/escribirarchivo.txt"));
                String[] lineas = {"Linea1", "Linea2", "Linea3", "Linea4", "Linea5"};
                for (String line : lineas) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
