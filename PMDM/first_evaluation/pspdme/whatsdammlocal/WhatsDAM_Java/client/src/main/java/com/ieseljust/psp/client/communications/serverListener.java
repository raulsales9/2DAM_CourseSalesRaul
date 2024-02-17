package com.ieseljust.psp.client.communications;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;
import com.ieseljust.psp.client.ViewModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;





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
    // 1. Crear un client de tipo servidor que escuche por el puerto de recepción de mensajes

    ServerSocket listener = null;
    try {
        // Crear el client en un puerto determinado por el sistema
        // y guardarlo en listenPort.
        //seversocket de 0 genera un puerto aleatorio disponibe (port pal servidor)
        listener = new ServerSocket(0);
        //mos dona el port
        CurrentConfig.setlistenPort(listener.getLocalPort());
        while (true) {
            Socket socket = listener.accept();
            try {
                // 3. Crear un client de tipo servidor que escuche por el puerto de recepción de mensajes
                //System.out.print("111111");
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                //System.out.println("3000 prints pa buscar el fallo");

                String line;
                //StringBuilder sb = new StringBuilder();
                //while ((line = br.readLine()) != null) {
                //    processNotification(line);
                //}
                line=br.readLine();
                String receivedMessage = line;

                        System.out.println(" REP "+receivedMessage);
                        JSONObject jsonMessage = new JSONObject(receivedMessage);

                        String messageType = jsonMessage.getString("type");

                        if (messageType.equals("userlist")) {
                            System.out.println("Userlist");
                            JSONArray userList = jsonMessage.getJSONArray("content");
                            ArrayList<String> combinedList = new ArrayList<>();

                            for (int i = 0; i < userList.length(); i++) {
                                combinedList.add(userList.getString(i));
                            }

                            combinedList.addAll(vm.getLlistaUsuaris());
                            vm.updateUserList(combinedList);
                        } else if (messageType.equals("message")) {
                            System.out.println("Mssage");
                            String username = jsonMessage.getString("user");
                            String content = jsonMessage.getString("content");
                            Message mensaje = new Message(username, content);
                            vm.addMessage(mensaje);
                        }

                        OutputStream outputStream = socket.getOutputStream();
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                        PrintWriter printWriter = new PrintWriter(outputStreamWriter);

                        System.out.println("Torna status ok");
                        String message = "{'status':'ok'}";
                        printWriter.println(message);
                        printWriter.flush();
                        printWriter.close();
                        outputStreamWriter.close();
                        outputStream.close();
                        br.close();
                        isr.close();
                        is.close();
                        //socket.close();

            } catch (IOException e) {
                System.out.println("La aceptación ha fallado.");
            }
        }
    } catch (IOException e) {
        System.out.println("El puerto " + listenerPort + " está ocupado o es inaccesible.");
        return;
    }
}


        // 1. Crear un client de tipus servidor que escolte pel port de recepció de
        // missatges
       
            // Creem el client en un port determinat pel sistema
            // i el guardem a listenPort.
           
                    // 3. Creem un client de tipus servidor que escolte pel port de recepció de
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
