package com.ieseljust.psp.server;

import java.net.Socket;

import org.json.JSONObject;

public class Connexio {

    // Aquesta classe modela les connexions
    // Cada connexió contindrà el nom de l'usuari, el socket pel 
    // que s'ha connectat i el número de port pel que espera rebre
    // les notificacions per part del servidor.

    private String user;
    private Socket socket;
    private int listenPort;

    public Connexio(String user, Socket socket, int listenPort){
        this.user = user;
        this.socket=socket;
        this.listenPort=listenPort;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user=user;
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket=socket;
    }

    public String getRemoteAddress() {
        return this.socket.getInetAddress().getHostAddress();
    }

    public int getRemotePort() {
        return this.listenPort;
    }

    @Override
    public String toString() {
        return user + " " + socket + " "+listenPort;
    }

}
