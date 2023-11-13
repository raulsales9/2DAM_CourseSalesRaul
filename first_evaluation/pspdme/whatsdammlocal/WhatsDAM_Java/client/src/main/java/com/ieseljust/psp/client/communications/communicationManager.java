package com.ieseljust.psp.client.communications;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class communicationManager {

    /* Aquesta classe s'encarrega de la gestió de la
     comunicació amb el servidor.
     */
    public static JSONObject sendServer(String msg) {
        
        
  /*       Socket socket=new Socket();
        InetSocketAddress socketAddr=new InetSocketAddress(CurrentConfig.server(), CurrentConfig.port());

        try {
            socket.connect(socketAddr);
            // Connexió realitzada amb èxit
            System.out.println("S'ha realitzat la connexió exitosament a "+socketAddr.toString()+". Ara procedirem a tancar-la.");

            // Obtenció dels streams d'entrada i eixida
            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();


            // Creem fluxos per a la lectura i escriptura de caràcters
            InputStreamReader isr=new InputStreamReader(is);
            OutputStreamWriter osw=new OutputStreamWriter(os);

            // Creem fluxos per a la lectura i escriptura de línies
            BufferedReader bReader=new BufferedReader(isr);
            PrintWriter pWriter=new PrintWriter(osw);

            // Escrivim al socket l'ordre GET del protocol HTTP per obtenir el document demanat
            System.out.println("Enviant: "+msg);
            pWriter.println(msg);
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
            
            JSONObject jsonResponse = new JSONObject(linia);
            //jsonResponse.put("respuesta", respuestaBuilder.toString());

            return jsonResponse;
        
        } catch (IOException | JSONException e) {
            System.out.println("Error: " + e);
        }
        
        return null;
            
*/

        

        try {
            Socket socket = new Socket(CurrentConfig.server(), CurrentConfig.port());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Enviant al server: "+msg.toString());
            out.println(msg);
            out.flush();

            
            // Lee la respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder respuestaBuilder = new StringBuilder();
            String linea;

            while ((linea = in.readLine()) != null) {
                System.out.println("linea: "+linea);
                respuestaBuilder.append(linea);
            }
           
            System.out.println("Rep: "+respuestaBuilder.toString());

           
            socket.close();

            // Crea un objeto JSON con la respuesta del servidor
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("respuesta", respuestaBuilder.toString());

            return jsonResponse;

        } catch (IOException | JSONException e) {
            System.out.println("Error: " + e);
        }

        return null;
    }

    public static void connect() throws JSONException, communicationManagerException {
        // Obtener la informacion de configuracion del servidor desde CurrentConfig
        String username = CurrentConfig.username();
        int listenPort = CurrentConfig.listenPort();

        // Crear el mensaje JSON con la orden "register", el nombre de usuario y el puerto de escucha
        JSONObject registerMessage = new JSONObject();
        registerMessage.put("command", "register");
        registerMessage.put("user", username);
        registerMessage.put("listenPort", listenPort);

        try {
            // Enviar el mensaje al servidor y recibir la respuesta
            JSONObject response = sendServer(registerMessage.toString());

            // Verificar la respuesta del servidor
            if (response.has("status") && response.getString("status").equals("error")) {
                // Si el servidor devuelve un estado de error, lanzar una excepcion personalizada
                throw new communicationManagerException(response.getString("message"));
            }

        } catch (communicationManagerException | JSONException e) {
            System.out.println("Error: " + e);
            // Manejar la excepcion segun tus necesidades
        }
    }

    public static void sendMessage(Message m) {
        // Envia un misstge al servidor (es fa amb una línia!)
    }
}