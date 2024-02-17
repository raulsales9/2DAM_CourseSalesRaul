package com.ieseljust.psp.client.communications;

// Excepció personalitzada per gestionar els nostre errors

public class communicationManagerException extends Exception{

    public communicationManagerException(String Message){
        super(Message);
    }
    
}
