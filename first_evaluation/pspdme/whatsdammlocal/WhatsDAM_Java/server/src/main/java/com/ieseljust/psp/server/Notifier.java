package com.ieseljust.psp.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Notifier {
    /*
     * Aquesta classe centralitza l'enviament d'informació
     * als subscriptors (clients).
     * 
     * Cada vegada que es modifique la llista de clients
     * registrats/subscrits, que s'envie un missatge o que
     * s'haja de notificar alguna cosa es farà des d'aquesta
     * classe.
     */

    // Llista de connexions, comuna a tots els objectes
    static ArrayList<Connexio> Connexions;

    public static void setConnexions(ArrayList<Connexio> _connexions) {
        // Rep les connexions per part de ServerMsg
        Connexions = _connexions;
    }

    protected static void sendSubscribers(String msg) {
        // Envía un missatge als usuaris subscrits
        // En cas que el client no estiga connectat, 
        // el llevarà de la llist de subscriptors

        // ArrayList per saber quins clients es desconnecten
        ArrayList<Connexio> desconnectats = new ArrayList<Connexio>();

        // Recorrem la llista de connexions
        for (Connexio connexio : Connexions) {

            // Creeem el socket per comunicar-nos als
            // listeners de cada client
            Socket socket = new Socket();

            /*DEBUG
            System.out.println("Local: "+connexio.getSocket().getLocalAddress()+":"+connexio.getSocket().getLocalPort());
            System.out.println("Remote: "+connexio.getSocket().getInetAddress().getHostAddress()+":"+connexio.getSocket().getPort());
             */

             // Creem el socket per comunicar-nos amb el servei d'escolta del client
            InetSocketAddress socketAddr = new InetSocketAddress(connexio.getRemoteAddress(), connexio.getRemotePort());           

            // Resposta
            JSONObject resposta = null;
            
            try {
                System.out.println(socketAddr.toString());
                socket.connect(socketAddr);

                // Connexió realitzada amb èxit
                // Obtenció dels streams d'entrada i eixida
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                // Creem fluxos per a la lectura i escriptura de caràcters
                InputStreamReader isr = new InputStreamReader(is);
                OutputStreamWriter osw = new OutputStreamWriter(os);

                // Creem fluxos per a la lectura i escriptura de línies
                BufferedReader bReader = new BufferedReader(isr);
                PrintWriter pWriter = new PrintWriter(osw);

                // Escrivim al socket el missatge
                System.out.println("Enviant: "+msg+" a "+connexio.getUser()+" en "+connexio.getRemoteAddress()+":"+connexio.getRemotePort());
                pWriter.println(msg);
                pWriter.flush();

                // Esperem si hi ha resposta
                String linia = bReader.readLine();
                // Si volem depurar la resposa que se'ns envia
                //System.out.println(linia);

                resposta = new JSONObject(linia);

                // Aci podriem analitzar la resposta
                // a través de la clau "status"

                pWriter.close();
                bReader.close();
                isr.close();
                osw.close();
                is.close();
                os.close();

                socket.close();

            } catch (ConnectException e) {
                System.out.println(connexio.toString());
                System.out.println("[Notifier] Excepció "+e.getMessage()+". Desconnectat l'usuari " + connexio.getUser());

                // Si es produeix un error en la connexió (connection refused)
                // El client està desconnectat, per tant, l'afegim a la
                // llista de desconnectats pe eliminar-lo després
                // No es pot fer ara perquè estem dins el bucle,i ens
                // donaria l'error java.util.ConcurrentModificationException
                desconnectats.add(connexio);
            } catch (SocketException e) {
                // Fem el mateix si es produeix una excepció accedint a algun buffer
                // ja que  el client pot interrompre el procés a mitjan  escriptura
                System.out.println("[Notifier] El client "+ connexio.getUser()+" s'ha desconnectat abruptament");
                desconnectats.add(connexio);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } // END FOR

        // Una vegada acabat el bucle, actualitzem la llista de connexions
        synchronized (Connexions) { // L'accés a connexions ha de ser sincronitzats
            for (Connexio connexio : desconnectats) {
                Connexions.remove(connexio);
            }

            // Si s'ha desconnectat algun usuari, hem de reenviar la llista d'usuaris
            if (desconnectats.size()>0) Notifier.broadCastUsers();
        }

    }

    public static void broadCastUsers() {
        // Envia a tots els usuaris registrats la
        // llista d'usuaris

        JSONArray jsUsers = new JSONArray();
        JSONObject userlist = new JSONObject();
        userlist.put("type", "userlist");
        for (Connexio connexio : Connexions)
            jsUsers.put(connexio.getUser());
        userlist.put("content", jsUsers);

        sendSubscribers(userlist.toString());

    }

    public static void pingClients() {
        // Envia a tots els usuaris registrats un "ping"
        // per veure si estan connectats

        sendSubscribers("{\"type\":\"ping\"}");

    }

    public static void broadCastMessage(JSONObject Msg) {
        // Envia a tots els usuaris registrats 
        // el missatge

        JSONObject msg = new JSONObject();
        msg.put("user", Msg.getString("user"));
        msg.put("content", Msg.getString("content"));
        msg.put("type", "message");        

        sendSubscribers(msg.toString());

    }

}
