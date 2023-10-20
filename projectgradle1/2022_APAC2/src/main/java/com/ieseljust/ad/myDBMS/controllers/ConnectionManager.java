package com.ieseljust.ad.myDBMS.controllers;

import java.sql.*;
import java.util.Scanner;

/**
 * 
 * @author pc-raul
 */

class ConnectionManager{
    
    String server;
    /**
     * @variable port he considerado que debe ser entero
     */
    int port;
    String user;
    String pass;
    
    //No se como diferenciar un constructor
    ConnectionManager(){
        this.server = "default_server";
        this.port = 3308;
        this.user = "default_user";
        this.pass = "default_password";
    }

    ConnectionManager(String server, int port, String user, String pass){
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    public Connection connectDBMS(){
        // TO-DO:  Create a connection, 
        //         Returns this or null.
        // Remember error management
        try{
            
        }catch(){
            
        }
        return null;

    }

    public void showInfo(){
        // TO-DO: show server info
        // Remember error management
        try{
            
        }catch(){
            
        }
    }

    public void showDatabases(){
         // TO-DO: Show databases in your server
    }

    public void startShell(){

        Scanner keyboard = new Scanner(System.in);
        String command;

        do {

            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"# ("+this.user+") on "+this.server+":"+this.port+"> "+ConsoleColors.RESET);
            command = keyboard.nextLine();

                        
            switch (command){
                case "sh db":
                case "show databases":
                    this.showDatabases();
                    break;
                
                case "info":
                    this.showInfo();
                    break;

                case "quit":
                    break;
                default:
                    // Com que no podem utilitzar expressions
                    // regulars en un case (per capturar un "use *")
                    // busquem aquest cas en el default:

                    String[] subcommand=command.split(" ");
                    switch (subcommand[0]){
                        case "use":
                            // TO-DO:
                                // Creem un objecte de tipus databaseManager per connectar-nos a
                                // la base de dades i iniciar una shell de manipulació de BD..

                        default:
                            System.out.println(ConsoleColors.RED+"Unknown option"+ConsoleColors.RESET);
                            break;


                    }

                    

            }
            
        } while(!command.equals("quit"));

        
        }


}