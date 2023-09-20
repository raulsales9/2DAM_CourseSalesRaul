package fileExercise;

import java.io.*;

public class fileinputoutput {

    public static void main(String[] args) {
        // Bytes read from source
        byte[] buffer = new byte[32];
        // Bytes (effectively) written to destination
        int bytes, bytesCopied = 0;

        FileInputStream fis = null;
        FileOutputStream fos = null;

        // To provide information about source
        File f;

        // Are the arguments ok?
        if (args.length != 2) {
            System.out.println("Número incorrecto de argumentos. Sintaxis:\nFileCopy archivoOrigen archivoDestino");
            return;
        }

        try {
            // Show source size
            f = new File(args[0]);
            System.out.println("Total: " + f.length() + " bytes");

            // Create streams
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            while ((bytes = fis.read(buffer)) != -1) {
                // Write to destination
                fos.write(buffer, 0, bytes);
                // Update the number of bytes
                bytesCopied += bytes;
                // Show progress
                System.out.print("\rCopiados " + bytesCopied + " bytes...");
            }
            System.out.println("\n¡Hecho!");

        } catch (IOException exc) {
            System.out.println("Error de entrada y salida: " + exc);
        } finally {
            // At the end, we have to close the files, whether an error
            // exists or not.
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException exc) {
                System.out.println("Error al cerrar el archivo de origen: " + exc);
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException exc) {
                System.out.println("Error al cerrar el archivo destino: " + exc);
            }
        }
    }
}
