package com.ieseljust.psp.client.communications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class communicationManager {

    /*
     * Aquesta classe s'encarrega de la gestió de la
     * comunicació amb el servidor.
     */

    public static JSONObject sendServer(String msg) throws IOException {

        // TO-DO:
        // Envía al servidor l'string msg
        // I retorna un JSON amb la resposta

        Socket socket = new Socket();
        InetSocketAddress socketAddr = new InetSocketAddress(CurrentConfig.server(), CurrentConfig.port());

        try {
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
            pWriter.println("Envie al server: " + msg);
            pWriter.flush();

            pWriter.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            JSONObject resposta = null;
            String linia;
            pWriter.println("bbbbbbbbbbbbbbbbbbb");
            while ((linia = bReader.readLine()) != null) {
                resposta = new JSONObject(linia);
            }
            pWriter.println("ccccccccccccccccccccccc");

            System.out.println("Rep: " + linia);

            pWriter.close();
            bReader.close();
            isr.close();
            osw.close();
            is.close();
            os.close();

            socket.close();
            return new JSONObject(resposta);
        } catch (IOException e) {
            System.out.println("Excepció en la connexió: " + e.getMessage());
            return new JSONObject("{'status':'error', 'message':'" + e.getMessage() + "'}");

        }

    }

    public static void connect() throws JSONException, communicationManagerException {

        // TO-DO:

        // Creem un misstge pe al servidor amb l'ordre (command) register,
        // el nom d'usuari (user) i el port (listenPost) pel qual el client escoltarà
        // les notificacions (el tenim a CurrentConfig.listenPort())

        // Enviarà el missatge al servidor a través de sendServer.

        // Si es produeix un error, llançarà una excepció i aturarà
        // l'aplicaió (per exemple, si l'usuari ja existeix al servidor)
        // Teniu per a això l'excepció communicationManagerException
        // com a excepció personalitzada al projecte.
        try {

            // messaje per al servidor (command) register,
            // user, port (listenPost)
            // currentConfig.listenPort()
            System.out.println("1111111111111111111111111111111111111111");
            JSONObject newmsg = new JSONObject();
            newmsg.put("command", "register");
            newmsg.put("user", CurrentConfig.username());
            newmsg.put("listenPort", CurrentConfig.listenPort());

            System.out.println(newmsg.toString());
            JSONObject resposta = sendServer(newmsg.toString());

            if (resposta.getString("status").equals("error")) {

            } else {
                throw new communicationManagerException(resposta.getString("message"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendMessage(Message m) {
        // Envia un misstge al servidor (es fa amb una línia!)
        try {
            sendServer(m.toJSONCommand().toString());
        } catch (IOException e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage());
        }
    }
}
