package filedeleter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;



public class filedeleter {

    public static void main(String[] args) throws Exception {
        BufferedReader fin;
        PrintWriter fout;

        if (args.length != 2) {
            System.out.println("El uso: indica fichero entrada y salida");
            return;
        }

        fin = new BufferedReader(new FileReader(args[0]));
        fout = new PrintWriter(new FileWriter(args[1]));

        String line;
        
        do {
            line = fin.readLine();
            if (line != null) {
                // per a borrar tot abans del primer espai
                int spaceIndex = line.indexOf(' ');
                if(spaceIndex != -1){
                    line = line.substring(spaceIndex + 1);
                }
                fout.println(line);
            }
        } while (line != null);
        fin.close();
        fout.close();
    }
}
