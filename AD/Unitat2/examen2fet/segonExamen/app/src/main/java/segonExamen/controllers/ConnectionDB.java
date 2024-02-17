/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package segonExamen.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc-raul
 */

public class ConnectionDB {
    String server;
    int port;
    String user;
    String pass;
    Connection dbConnection;

    public ConnectionDB(String server, int port, String username, String passwd) {
        this.server = server;
        this.port = port;
        this.user = username;
        this.pass = passwd;
        this.dbConnection = null;
    }

    public Connection connectDBMS() {
        try {
            String dbUrl = "jdbc:mysql://" + server + ":" + port + "/";
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Conexión al DBMS exitosa.");
            return dbConnection;
        } catch (SQLException e) {
            System.err.println("La conexión al DBMS falló: " + e.getMessage());
            return null;
        }
    }

    public void showInfo() {
        System.out.println("Servidor DBMS: " + server);
        System.out.println("Puerto DBMS: " + port);
        System.out.println("Usuario: " + user);
    }
}
