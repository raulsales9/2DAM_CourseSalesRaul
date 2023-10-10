
package readWriteFiles;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author pc-raul
 */

public class ManageFiles {

    public static void main(String[] args) {
        escribirArchivo archivo = new escribirArchivo();
        archivo.escribirArchivo();
        
        leerFichero lector = new leerFichero();
        lector.leerFichero();
    }

    static class leerFichero {

        public void leerFichero() {
            try {
                FileReader file = new FileReader("/home/pc-raul/NetBeansProjects/repaso/escribirarchivo.txt");

                int c = 0;
                while (c != -1) {
                    c = file.read();
                    if (c != -1) {
                        char letra = (char) c;
                        System.out.print(letra);
                    }
                }
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class escribirArchivo {

        public void escribirArchivo() {
            String frase = "esto es una prueba de escritura";
            try (FileWriter file = new FileWriter("/home/pc-raul/NetBeansProjects/repaso/escribirarchivo.txt")) {
                for (int i = 0; i < frase.length(); i++) {
                    file.write(frase.charAt(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
