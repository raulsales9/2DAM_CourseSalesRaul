package com.ieseljust.psp.client.communications;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;
import com.ieseljust.psp.client.ViewModel;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.json.JSONArray;

public class serverListener implements Runnable {

    /*
     * Aquesta classe s'encarrega de gestionar els broadcasts que fa el servidor
     * cap als clients subscrits a les seues publicacions.
     * Implementarà doncs un servei que escoltarà en un port aleatori el que
     * li envia el servidor de missatgeria i ho processarà de forma adeqüada.
     * 
     */

    ViewModel vm;

    public serverListener(ViewModel vm) {
        this.vm = vm;
    }

    int listenerPort = CurrentConfig.listenPort();

    @Override
public void run() {
    // 1. Crear un socket de tipo servidor que escuche por el puerto de recepción de mensajes
    ServerSocket listener = null;
    try {
        // Crear el socket en un puerto determinado por el sistema
        // y guardarlo en listenPort.
        listener = new ServerSocket(0);
        CurrentConfig.setlistenPort(listener.getLocalPort());

        while (true) {
            try {
                // 3. Crear un socket de tipo servidor que escuche por el puerto de recepción de mensajes
                Socket client = listener.accept();
                InputStream is = client.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String line;
                while ((line = br.readLine()) != null) {
                    processNotification(line);
                }

                client.close();
            } catch (IOException e) {
                System.out.println("La aceptación ha fallado.");
            }
        }
    } catch (IOException e) {
        System.out.println("El puerto " + listenerPort + " está ocupado o es inaccesible.");
        return;
    }
}

private void processNotification(String notification) {
    try {
        JSONObject json = new JSONObject(notification);
        String type = json.getString("type");

        if ("userlist".equals(type)) {
            JSONArray userList = json.getJSONArray("content");
            ArrayList<String> users = new ArrayList<>();
            for (int i = 0; i < userList.length(); i++) {
                users.add(userList.getString(i));
            }
            vm.updateUserList(users);
        } else if ("message".equals(type)) {
            String user = json.getString("user");
            String content = json.getString("content");
            vm.addMessage(new Message(user, content));
        }
    } catch (Exception e) {
        System.out.println("Error al procesar notificación: " + e.getMessage());
    }
}

        // 1. Crear un socket de tipus servidor que escolte pel port de recepció de
        // missatges
       
            // Creem el socket en un port determinat pel sistema
            // i el guardem a listenPort.
           
                    // 3. Creem un socket de tipus servidor que escolte pel port de recepció de
                    // missatges
                    
               

        // TO-DO
        // 2. Iniciem un bucle infinit a l'espera de rebre connexions
        // Quan arribe una connexió la processrem de manera adeqüada
        // Les peticions que podme rebre seran de tipus:

        // {"type": "userlist", "content": [Llista d'usuaris]}, amb un JSONArray amb la
        // llista d'usuaris.
        // {"type": "message", "user":"usuari", "content": "Contingut del missatge" }

        // És interessant implementar un mètode a banda per processat aquestes línies
        // però no cal que creem un fil a propòsit per atendre cada missatge, ja que
        // no som un servidor com a tal, i el que estem fent aci, és mantindre un
        // canal de recepció només amb el servidor.
    

}
