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
    int port;
    String user;
    String pass;
    Connection dbConnection;

    // Default constructor with default connection parameters
    ConnectionManager() {
        this.server = "default_server";
        this.port = 3308;
        this.user = "default_user";
        this.pass = "default_password";
    }

    // Constructor with custom connection parameters
    ConnectionManager(String server, int port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    /**
     * Establish a connection to the DBMS using the provided parameters.
     * @return The established database connection.
     */
    public Connection connectDBMS() {
        try {
            String dbUrl = "jdbc:mysql://" + server + ":" + port + "/";
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connection to the DBMS.");
            return dbConnection;
        } catch (SQLException e) {
            System.err.println("Connection to the DBMS failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Display the connection information (server, port, user).
     */
    public void showInfo() {
        System.out.println("DBMS Server: " + server);
        System.out.println("DBMS Port: " + port);
        System.out.println("User: " + user);
    }

    /**
     * Retrieve and display the list of databases on the connected DBMS.
     * @throws SQLException if there is an issue executing the SQL command.
     */
    public void showDatabases() throws SQLException {
        Statement statement = dbConnection.createStatement();
        String command = "SHOW DATABASES";

        ResultSet resultSet = statement.executeQuery(command);
        System.out.println("The list of databases:");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
    }

    /**
     * Import SQL scripts from a file and execute them.
     * @param script The path to the SQL script file.
     * @throws SQLException if there is an issue executing the SQL commands.
     */
    public void importScript(String script) throws SQLException {
        try {
            File file = new File(script);
            StringBuilder scriptBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                scriptBuilder.append(line).append("\n");
            }
            reader.close();

            Statement statement = dbConnection.createStatement();

            // Split the script into individual SQL commands using ";" as the delimiter
            String[] sqlCommands = scriptBuilder.toString().split(";");

            for (String sqlCommand : sqlCommands) {
                String trimmedCommand = sqlCommand.trim();
                if (!trimmedCommand.isEmpty() && !trimmedCommand.startsWith("--")) {
                    statement.execute(trimmedCommand);
                }
            }

            System.out.println("Script imported successfully");
        } catch (IOException e) {
            System.err.println("Error importing script: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error executing SQL script: " + e.getMessage());
        }
    }

    /**
     * Start a shell interface for interacting with the DBMS.
     * Supports commands like "sh db" (show databases), "info" (show connection info),
     * "import <script_name>" (import an SQL script), "use <database_name>" (switch to a database),
     * and "quit" (exit the shell).
     * @throws SQLException if there is an issue executing SQL commands.
     */
    public void startShell() throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        String command;

        do {
            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "# (" + this.user + ") on " + this.server + ":" + this.port + "> " + ConsoleColors.RESET);
            command = keyboard.nextLine();
            System.out.println("Available commands:");
            System.out.println("sh db or show databases - Show the list of databases");
            System.out.println("info - Check connection credentials");
            System.out.println("import <script_name> - Import and execute an SQL script");
            System.out.println("use <database_name> - Switch to a specific database");
            System.out.println("quit - Exit the shell\n");

            String[] parts = command.split(" ");
            String action = parts[0];

            switch (action) {
                case "sh db":
                case "show databases":
                    this.showDatabases();
                    break;

                case "info":
                    this.showInfo();
                    break;

                case "quit":
                    System.out.println("Closing the shell");
                    break;

                case "use":
                    if (parts.length > 1) {
                        String databaseName = parts[1];
                        useDatabase(databaseName);
                    } else {
                        System.out.println(ConsoleColors.RED + "Usage: use <database_name>" + ConsoleColors.RESET);
                    }
                    break;

                case "import":
                    if (parts.length > 1) {
                        String scriptName = "scripts/" + parts[1];
                        this.importScript(scriptName);
                    } else {
                        System.out.println(ConsoleColors.RED + "Usage: import <script_name>" + ConsoleColors.RESET);
                    }
                    break;

                default:
                    System.out.println(ConsoleColors.RED + "Unknown option" + ConsoleColors.RESET);
                    break;
            }
        } while (!command.equals("quit"));

        disconnectDBMS(); // Call the method to close the connection
    }

    /**
     * Switch to a specific database by creating a DatabaseManager instance.
     * @param databaseName The name of the database to switch to.
     * @return The DatabaseManager instance for the selected database.
     */
    public DatabaseManager useDatabase(String databaseName) {
        DatabaseManager dbManager = new DatabaseManager(this.server, this.port, this.user, this.pass, databaseName);
        dbManager.connectDatabase();
        return dbManager;
    }

    /**
     * Close the connection to the DBMS.
     */
    public void disconnectDBMS() {
        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                System.err.println("Error disconnecting: " + e.getMessage());
            }
        }
    }
}