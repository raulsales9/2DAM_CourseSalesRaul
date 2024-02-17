/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc-raul
 */
class ConnectionManager {

    String server;
    String port;
    String user;
    String pass;
    Connection dbConnection;

    // Default constructor with default connection parameters
    ConnectionManager() {
        this.server = "default_server";
        this.port = "3308";
        this.user = "default_user";
        this.pass = "default_password";
    }

    // Constructor with custom connection parameters
    ConnectionManager(String server, String port, String user, String pass) {
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
}
