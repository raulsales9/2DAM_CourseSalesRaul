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

import org.json.JSONException;
import org.json.JSONObject;

public class communicationManager {

    /* Aquesta classe s'encarrega de la gestió de la
     comunicació amb el servidor.
     */
    
    public static JSONObject sendServer(String msg) {

        // TO-DO:
        // Envía al servidor l'string msg
        // I retorna un JSON amb la resposta

        
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
        


    }

    public static void sendMessage(Message m){
        // Envia un misstge al servidor (es fa amb una línia!)
    }    
}
