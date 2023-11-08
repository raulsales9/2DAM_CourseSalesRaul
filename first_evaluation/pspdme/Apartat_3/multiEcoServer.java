import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class multiEcoServer {
    
    public void listen() throws IOException{
        System.out.println("Iniciant el servidor...");

        ServerSocket listener=null;
        int srvPort=9999;
        try {
            listener=new ServerSocket(srvPort);
        } catch (IOException e) {
            System.out.println("El port "+srvPort+" està ocupato és inaccessible.");
            return;
        }

        // Iniciem un bucle infinit a l'espera de rebre connexions
        while (true){
            Socket socket=listener.accept();

            // L'ordre anterior és bloquejant, quan arriba aci, ha rebut una connexio
            System.out.println("S'ha rebut la connexió. Generant resposta.");

            // Creem un objecte de tipus generadorEco i el llancem en un thread
            generadorEco generador=new generadorEco(socket);
            Thread generadorThread=new Thread(generador);
            generadorThread.start();

        }
    }

    public static void main(String[] args) throws IOException {
        
        multiEcoServer es=new multiEcoServer();
        es.listen();

    }

    
}