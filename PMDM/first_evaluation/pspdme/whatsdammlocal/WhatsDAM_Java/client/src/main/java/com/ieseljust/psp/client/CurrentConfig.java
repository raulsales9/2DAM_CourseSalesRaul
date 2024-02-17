package com.ieseljust.psp.client;

public class CurrentConfig {

    static String _username="";
    static String _server="127.0.0.1";
    static int _port=9999;
    static int _listenport;

    public static void init(String server,String username){
        _username=username;
        _server=server;
    }


    public static String server(){ return _server; }
    public static String username(){ return _username; }
    public static int port(){ return _port; }
    public static Boolean connectionReady(){ 
        if (_listenport!=0) return true;
        else return false;
     }
    public static int listenPort(){ 
        return _listenport; 
    }
    public static void setlistenPort(int port){ 
        System.out.println("Setting Listen Port to "+port);
        _listenport=port; 
    }

}
