import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ConnectAndShowWithResources {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Has d'indicar una adreça web, un port i un recurs");
            System.exit(-1);
        }

        String dst = args[0];
        int portDst = Integer.parseInt(args[1]);

        InetSocketAddress socketAddr = new InetSocketAddress(dst, portDst);

        try (Socket socket = new Socket()) {
            socket.connect(socketAddr);
            try (BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));) {

                // Connexió realitzada amb èxit
                System.out.println("S'ha realitzat la connexió exitosament a " + socketAddr.toString()
                        + ". Ara procedirem a tancar-la.");

                // Escrivim al socket l'ordre GET del protocol HTTP per obtenir el document
                // demanat
                String command = "GET /" + args[2] + "\r\n\r\n";
                System.out.println("HTTP Request: " + command);
                pWriter.println(command);
                pWriter.flush();

                String linia;
                while ((linia = bReader.readLine()) != null) {
                    System.out.println(linia);
                }

                /*
                 * pWriter.close();
                 * bReader.close();
                 * isr.close();
                 * osw.close();
                 * is.close();
                 * os.close();
                 */
                socket.close();

            } catch (Exception e) {
                throw (e);
            }

        } catch (IOException e) {
            System.out.println("Excepció en la connexió: " + e.getMessage());
        }

    }
}
