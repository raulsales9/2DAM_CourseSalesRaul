package com.ieseljust.ad.myDBMS;

import com.ieseljust.ad.myDBMS.*;
import java.sql.*;
import java.util.Scanner;

/**
 * 
 * @author pc-raul
 */

class ConnectionManager{
    
    String server;
    /**
     * @variable port he considerado que debe ser entero, a diferencia de lo que nos proporciona el fichero inicial
     *RE: i consider to convert the int to string to test first
     **/
    //int port;
    String port;
    String user;
    String pass;
    Connection dbConnection;
    
    //No se como diferenciar un constructor en java es muy raro
    ConnectionManager(){
        this.server = "default_server";
        this.port = "3308";
        this.user = "default_user";
        this.pass = "default_password";
    }

    ConnectionManager(String server, String port, String user, String pass){
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    /***
     * 
     * Try/catch para manejar excepciones con la conexion. 
     * 
     * 
     */
    public Connection connectDBMS(){
        try{
            String dbUrl  = "jdbc:mysql://" + server + ":" + port + "/";
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connection to the DMBS.");
            return dbConnection;
        }catch(SQLException e){
            System.err.println("Connection to DMBS failed" + e.getMessage());
            return null;
        }
    }

    /***
     * We display the connection information by means of the connection information
     */
    public void showInfo(){
        System.out.println("DBMS Server: " + server);
        System.out.println("DBMS Port: " + port);
        System.out.println("User: " + user);
    }

    /***
     * // TO-DO: Show databases in your server
     * Using statements we can execute anything.
     * the next will be execute the query to keep it in a resultset
     */
    public void showDatabases() throws SQLException{
         Statement statement = dbConnection.createStatement();
         String comand = "SHOW DATABASES";
         
         ResultSet resultset = statement.executeQuery(comand);
            System.out.println("The list of the databases");
            while(resultset.next()){
                System.out.println(resultset.getString(1));
            }
            resultset.close();
    }

    public void startShell() throws SQLException{
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
                    System.out.println("closing the shell");
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
                                //if(subcomand.length == 2){
                                //    DatabaseManager dbManager = new DatabaseManager(server, port, user, pass);
                                //}
                        default:
                            System.out.println(ConsoleColors.RED+"Unknown option"+ConsoleColors.RESET);
                            break;
                    }
            }
        } while(!command.equals("quit"));
        }
}