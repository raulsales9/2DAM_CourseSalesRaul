package com.ieseljust.ad.myDBMS;

import com.ieseljust.ad.myDBMS.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * 
 * @author pc-raul
 */

class ConnectionManager {

    String server;
    /**
     * @variable port he considerado que debe ser entero, a diferencia de lo que nos
     *           proporciona el fichero inicial
     *           RE: i consider to convert the int to string to test first
     **/
    // int port;
    String port;
    String user;
    String pass;
    Connection dbConnection;

    // No se como diferenciar un constructor en java es muy raro
    ConnectionManager() {
        this.server = "default_server";
        this.port = "3308";
        this.user = "default_user";
        this.pass = "default_password";
    }

    ConnectionManager(String server, String port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    /***
     * 
     * Try/catch para manejar excepciones con la conexion.
     * with the jdbc driver we create a connection to the database
     * 
     */
    public Connection connectDBMS() {
        try {
            String dbUrl = "jdbc:mysql://" + server + ":" + port + "/";
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connection to the DMBS.");
            return dbConnection;
        } catch (SQLException e) {
            System.err.println("Connection to DMBS failed" + e.getMessage());
            return null;
        }
    }

    /*
     * if we want to use another db, we can change the driver to use another db.
     */
    // public Connection connectDBMSqlite() {
    //    Class.forname= "org.sqlite.JDBC";
    //    try {
    //        String dbUrl = "jdbc:sqlite://" + server + ":" + port + "/";
    //        dbConnection = DriverManager.getConnection(dbUrl, user, pass);
    //        System.out.println("Connection to the DMBS.");
    //        return dbConnection;
    //    } catch (SQLException e) {
    //        System.err.println("Connection to DMBS failed" + e.getMessage());
    //        return null;
    //    }
    //}

    /***
     * We display the connection information by means of the connection information
     * to show the server, port and user stats we just use a println with the values, and the final client will type a info to call this function (startShell)
     */
    public void showInfo() {
        System.out.println("DBMS Server: " + server);
        System.out.println("DBMS Port: " + port);
        System.out.println("User: " + user);
    }

    /***
     * // TO-DO: Show databases in your server
     * Using statements we can execute anything.
     * the next will be execute the query to keep it in a resultset
     */
    public void showDatabases() throws SQLException {
        Statement statement = dbConnection.createStatement();
        String comand = "SHOW DATABASES";

        ResultSet resultset = statement.executeQuery(comand);
        System.out.println("The list of the databases");
        while (resultset.next()) {
            System.out.println(resultset.getString(1));
        }
        resultset.close();
    }

    /*
     * Import scripts from a file. 
     * i was put a two catch to manage the exception, because before put the for with condition, the import was broken.
     */
    public void importScript(String script) throws SQLException {
        try {
            File file = new File(script);
            StringBuilder scriptbl = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                scriptbl.append(line).append("\n");
            }
            reader.close();

            Statement statement = dbConnection.createStatement();

            // Dividir el script en instrucciones individuales usando ";" como delimitador
            String[] sqlCommands = scriptbl.toString().split(";");

            for (String sqlCommand : sqlCommands) {
                String trimmedCommand = sqlCommand.trim();
                if (!trimmedCommand.isEmpty() && !trimmedCommand.startsWith("--")) {
                    statement.execute(trimmedCommand);
                }
            }

            
            System.out.println("Script imported successfully");
        } catch (IOException e) {
            System.err.println("Error importing script");
        } catch (SQLException e) {
            System.err.println("Error executing SQL script: " + e.getMessage());
        }
    }
    /**
     * Meanwhile we don't type quit, we will execute some sql commands. For example sh db or show databse with the same condition.
     * 
     *  */
    public void startShell() throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        String command;

        do {
            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "# (" + this.user + ") on " + this.server + ":" + this.port + "> " + ConsoleColors.RESET);
            command = keyboard.nextLine();
            System.out.println("sh db o show databases to see the list of the databases \n");
            System.out.println("info to check the credentials of your conexion \n");
            System.out.println("import +nameScript sql for importar-him \n");
            System.out.println("use the database of what you want \n");
            System.out.println("quit to exit\n");

            //Switch to make a callcenter with the commands introduced
            switch (command) {
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
                    // Buscar si el comando empieza con "use"
                    if (command.startsWith("use ")) {
                        String databaseName = command.substring(4).trim();
                        useDatabase(databaseName);
                    } else if (command.startsWith("import ")) {
                        String scriptName = "scripts/" + command.substring(7).trim();
                        this.importScript(scriptName);
                    } else {
                        System.out.println(ConsoleColors.RED + "Unknown option" + ConsoleColors.RESET);
                    }
                    break;
            }
        } while (!command.equals("quit"));

        // Call the method to close connection
        disconnectDBMS();
    }

    public void useDatabase(String databaseName) {
        // TO-DO: Implementar la lógica para cambiar a modo de base de datos
        // Puedes crear una instancia de DatabaseManager y llamar a su método startShell
        DatabaseManager dbManager = new DatabaseManager(this.server, this.port, this.user, this.pass, databaseName);
        dbManager.connectDatabase();
        dbManager.startShell();
    }

    /**
     * to make a good practice we should disconect the conexion to the database
     */
    public void disconnectDBMS() {
        // TO-DO: Implementar la lógica para desconectar de la base de datos
        // if don't have connection
        if (dbConnection != null) {
            try {
                //Close the connection
                dbConnection.close();
            } catch (SQLException e) {
                System.err.println("Error al desconectar: " + e.getMessage());
            }
        }
    }

}