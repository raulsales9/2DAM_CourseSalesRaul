import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;


public class ecoClient {
    public static void main(String[] args) {

        if (args.length!=2) {
            System.out.println("Has d'indicar una adreça web i un misstge");
            System.exit(-1);
        }

        String dst=args[0];
        
        // El port està definit pel protocol
        int portDst=9999;

        Socket socket=new Socket();
        InetSocketAddress socketAddr=new InetSocketAddress(dst, portDst);

        try {
            socket.connect(socketAddr);
            // Connexió realitzada amb èxit
            // Obtenció dels streams d'entrada i eixida
            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();


            // Creem fluxos per a la lectura i escriptura de caràcters
            InputStreamReader isr=new InputStreamReader(is);
            OutputStreamWriter osw=new OutputStreamWriter(os);

            // Creem fluxos per a la lectura i escriptura de línies
            BufferedReader bReader=new BufferedReader(isr);
            PrintWriter pWriter=new PrintWriter(osw);

            // Escrivim al socket el missatge
            pWriter.println(args[1]);
            pWriter.flush();


            String linia;
            while ((linia=bReader.readLine()) != null ){
                System.out.println(linia);
            }
            
            pWriter.close();
            bReader.close();
            isr.close();
            osw.close();
            is.close();
            os.close();

            socket.close();

        } catch (IOException e) {
            System.out.println("Excepció en la connexió: "+e.getMessage());
        }  
        
    }
}
