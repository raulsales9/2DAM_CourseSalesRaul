import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
 import java.io.PrintWriter;

public class ecoServer {
    
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
            System.out.println("S'ha rebut la connexió");

            // Ara hem de llegir què ens envíen
            InputStream is=socket.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader bf=new BufferedReader(isr);
            String linia=bf.readLine();

            String resposta;
            
            System.out.println("<log> Received... "+linia);
            // Analitzem la línia, i en funció d'aquesta retornem un resultat
            switch (linia){
                case "Hola don Pepito":
                    resposta="Hola don José";
                    break;

                case "Pasó usted por mi casa?":
                    resposta="Por su casa yo pasé";
                    break;
                case "Y vio usted a mi abuela?":
                    resposta="A su abuela yo la vi";
                    break;
                case "Adiós don Pepito":
                    resposta="Adiós don José";
                    break;
                default:
                    resposta="No es reconeix el misstge...";
                    break;
            }

            try{
                System.out.println("Esperant 3 segons...");
                Thread.sleep(3000);
            } catch (InterruptedException ie){
                System.out.println("S'ha inerromput la pausa...");
            };
            
            // Escrivim el resultat a l'stream d'eixida
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os);
            pw.write(resposta+"\n");
            pw.flush();

        // Pausa abans de tancar la connexió
        // Descomentar:
           try{
                System.out.println("Esperant 8 segons...");
                Thread.sleep(8000);
            } catch (InterruptedException ie){
                System.out.println("S'ha inerromput la pausa...");
            };

        
            pw.close();
            os.close();
    
        }
    }

    public static void main(String[] args) throws IOException {   
        ecoServer es=new ecoServer();
        es.listen();

    }
}

