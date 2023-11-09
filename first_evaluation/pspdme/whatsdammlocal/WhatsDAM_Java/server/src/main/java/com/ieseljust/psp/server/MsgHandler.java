package com.ieseljust.psp.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import org.json.JSONObject;

class MsgHandler implements Runnable {
    // Classe per atendre les peticions a través de threads

    private Socket MySocket; // Socket que atendrà la petició
    private ArrayList<Connexio> Connexions; // Vector de connexions del servidor

    MsgHandler(Socket socket, ArrayList<Connexio> connexions) {
        // Constructor de la classe.
        // S'inicia amb un socket i la llista de connexions.
        // Aquesta llista de connexions només podrà ser modificada
        // per un fil en la seua secció crítica

        MySocket = socket;
        Connexions = connexions;
    }

    JSONObject sendMessage(JSONObject MissatgeRebut) {
        // Rep un missatge en format JSON i l'envia a través
        // de la classe Notifier a tots els usuaris connectats
        // fent ús del mètode broadCastMessage.
        // Retornarà un JSONObject amb la resposta que ens
        // retorne aquest mètode.

        JSONObject resposta = new JSONObject();

        try {
            Notifier.broadCastMessage(MissatgeRebut);
            resposta.put("status", "ok");

        } catch (Exception e) {
            resposta.put("error", e.getMessage());
        }
        return resposta;

    }

    JSONObject registerUser(JSONObject MissatgeRebut) {
        // Mètode per registrar l'usuari

        JSONObject resposta = new JSONObject();

        // Recorre totes les connexions existents, i comprova si existeix
        // un usuari amb el mateix nom.

        for (Connexio connexio : Connexions) {
            System.out.println(connexio.toString());
            if (MissatgeRebut.getString("user").equals(connexio.getUser())) {
                // Si hi ha un usuari amb el mateix nom, retorna un missatge d'error
                resposta.put("status", "error");
                resposta.put("message", "User " + connexio.getUser() + " already registered");
                return resposta;
            }
        }

        // En cas que no existisca, crea una nova connexió amb l'usuari
        synchronized (Connexions) {
            Connexio con = new Connexio(MissatgeRebut.getString("user"), MySocket, MissatgeRebut.getInt("listenPort"));
            Connexions.add(con);
        }

        resposta.put("status", "ok");

        // I com que s'ha fet una actualizació de l'estat al
        // servidor, enviem un broadcast a tots els clients
        // amb la llista d'usuaris (a través del mètode broadCastUsers)
        Notifier.broadCastUsers();

        return resposta;
    }

    @Override
    public void run() {
        // Mètode que s'encarrega d'executar el fil
        try {
            // TO-DO
            // Aquest mètode és el que s'encarregarà d'atendre cada petició i generar la
            // resposta adequada.

            // Per a això

            // 1. Llegirem les línies a través de l'InputStream del socket amb què s'ha
            // obert la connexió (només se'ns passarà una línia per petició)
            //punt1
            InputStream is = MySocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            //punt 2
             JSONObject MissatgeRebut = new JSONObject(line);
             //punt3
             String command = MissatgeRebut.getString("command");
             //punt4
            JSONObject resposta;
            switch (command) {
                case "register":
                    resposta = registerUser(MissatgeRebut);
                    break;
                case "newMessage":
                    resposta = sendMessage(MissatgeRebut);
                    break;
                default:
                    resposta = new JSONObject();
                    resposta.put("status", "error");
                    resposta.put("error", "Comando desconocido: " + command);
                    break;
            }

            //punt5
            OutputStream os = MySocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println(resposta.toString());
            /*
             * 2. Una vegada tinguem la línia llegida, caldrà convertir-la a objecte JSON
             * amb:
             * 
             * JSONObject MissatgeRebut = new JSONObject(linia);
             * 
             * Aquest objecte tindrà la forma
             * 
             * {"command": ordre_a_executar, ...}
             * 
             * per tant:
             * 
             * 3. Obtenim l'ordre (camp "command") del JSON per tal d'obtindre què ens
             * demana el client.
             * 
             * Aquestes ordres podran ser:
             * 
             * - "register": Registra l'usuari al servidor, afegint-lo a la llista de
             * connexions.
             * Tingueu en compte que ja disposeu d'un mètode RegisterUser en aquesta
             * cuasse que implementa aquesta funcionalitat.
             *
             * - "newMessage": Es rep un misstge per enviar a la resta de clients. Recordeu
             * que
             * també disposeu d'un mètode sendMessage que envía un missatge
             * a tots els clients.
             * 
             * Cada petició haurà de generar una resposta JSON amb el següent format:
             * 
             * {"status": "ok"} si tot és correcte, o
             * 
             * {"status": "error", "error":"Missatge d'error"}
             * 
             * i enviar-la, codificada en un string al client a través del socket.
             * 
             * Consell: Implementar un mètode per atendre cada tipus de missatge,
             * en lloc de fer-ho tot al case.
             * 
             */

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
