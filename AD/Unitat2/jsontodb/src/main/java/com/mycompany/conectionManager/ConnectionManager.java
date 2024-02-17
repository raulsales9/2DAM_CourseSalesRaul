package com.mycompany.conectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
    private String server;
    private String port;
    private String user;
    private String pass;

    public ConnectionManager(String server, String port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    public Connection connectDBMS() throws SQLException {
        String dbUrl = "jdbc:mysql://" + server + ":" + port;
        return DriverManager.getConnection(dbUrl, user, pass);
    }

    public void disconnectDBMS(Connection dbConnection) {
        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}