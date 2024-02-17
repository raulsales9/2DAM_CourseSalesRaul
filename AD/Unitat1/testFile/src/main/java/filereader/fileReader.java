package filereader;

import java.io.*;

class fileReader {

    public static void main(String[] args) throws Exception {

        File dir; // Source dir// actual dir
// Collection of files from that dir
        File[] files;

        // readed characters
        int characters;

        // Input and Output Streams
        FileReader fin = null;
        FileWriter fout = null;

        // Check the args
        if (args.length != 2) {
            System.out.println("Número incorrecto de argumentos. Sintaxis:\n mergeTexts DirectorioOrigen archivoDestino");
            return;
        }

        try {

            // We get the list of Files
            dir = new File(args[0]);
            files = dir.listFiles();
            // Open and close output stream (in order to create thefile)
            fout = new FileWriter(args[1]);
            fout.close();

            // Re-open it
            fout = new FileWriter(args[1], true);

// Iterate among the list
            for (int i = 0; i < files.length; i++) {
                // open input stream
                fin = new FileReader(args[0] + "/" + files[i].getName());
                System.out.println("Merging " + args[0] + System.getProperty("file.separator") + files[i].getName());
                // and merge to the output one
                do {
                    characters = fin.read();
                    if (characters != -1) {
                        fout.write(characters);
                    }
                } while (characters != -1);
                fin.close(); //close the file merged

            }
            fout.close(); //close the output file

        } catch (IOException exc) {
            // Catch all the exception (we coud improve it)
            System.out.println("Error de E/S: " + exc);
        }
    }
}
