import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.io.IOException;

public class Connect {
    public static void main(String[] args) {

        if (args.length!=2) {
            System.out.println("Has d'indicar una adreça web i un port");
            System.exit(-1);
        }

        String dst=args[0];
        int portDst=Integer.parseInt(args[1]);

        Socket socket=new Socket();
        InetSocketAddress socketAddr=new InetSocketAddress(dst, portDst);

        try {
            socket.connect(socketAddr);
            // Connexió realitzada amb èxit
            System.out.println("S'ha realitzat la connexió exitosament a "+socketAddr.toString()+". Ara procedirem a tancar-la.");
            // Tancament d ela connexió
            socket.close();
        } catch (IOException e) {
            System.out.println("Excepció en la connexió: "+e.getMessage());
        }  
    }
}
