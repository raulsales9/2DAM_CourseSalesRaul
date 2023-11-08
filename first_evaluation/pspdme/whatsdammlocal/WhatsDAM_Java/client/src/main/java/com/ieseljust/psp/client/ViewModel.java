package com.ieseljust.psp.client;

import java.util.ArrayList;

import org.json.JSONObject;


public class ViewModel {
    /*
    S'encarrega d'emmagatzemar les dades necessàries per a 
    les vistes: Llista d'usuaris i missatges
    */

    // Implementació del patró Singleton
    // per a que la instància del ViewModel siga única
    static ViewModel INSTANCE = new ViewModel();

    // Llistes d'usuaris i missatges pendents de passar a la UI
    public static ArrayList<Message> llistaMissatges = new ArrayList<Message>();
    public static ArrayList<String> llistaUsuaris = new ArrayList<String>();

    public static ViewModel getInstance(){
        return INSTANCE;
    }

    // retorna la llista d'usuaris;
    public ArrayList<String> getLlistaUsuaris(){
        try{
        return llistaUsuaris;
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    // retorna els missatges pendents;
    public ArrayList<Message> getPendingMessages(){
        try{
            ArrayList <Message> ret=new ArrayList<>(llistaMissatges);
            llistaMissatges.clear();
            return ret; // Cal netejar-la
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }


    // Actualitza la llista d'usuaris
    public void updateUserList(ArrayList<String> users){
        llistaUsuaris=users;
    }

    public void addMessage(Message msg){
        llistaMissatges.add(msg);
    }

}

