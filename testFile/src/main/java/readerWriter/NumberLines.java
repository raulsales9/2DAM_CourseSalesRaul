package readerWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;



public class NumberLines {

    public static void Main(String[] args) throws Exception {
        BufferedReader fin;
        PrintWriter fout;

        int num_lines;
        String line;

        if (args.length != 2) {
            System.out.println("");
            return;
        }

        fin = new BufferedReader(new FileReader(args[0]));
        fout = new PrintWriter(new FileWriter(args[1]));

        num_lines = 1;
        do {
            line = fin.readLine();
            if (line != null) {
                fout.println(num_lines + ". " + line);
            }
            num_lines++;

        } while (line != null);
        fin.close();
        fout.close();
    }
}
