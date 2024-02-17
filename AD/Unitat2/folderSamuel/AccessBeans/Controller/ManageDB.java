package com.ieseljust.AccessBeans.Controller;

import com.ieseljust.AccessBeans.JocBean;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author samuel
 */


public class ManageDB {

    private Connection conn = null;

//    String connection = "jdbc:mysql://localhost:33333/BDJocs?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
    String connection = "jdbc:mysql://localhost:33333/BDJocs?useUnicode=true&allowMultipleQueries=true&characterEncoding=UTF-8&user=root&password=root";

    public Connection openConnection(String server, String user, String pass) throws SQLException {

        String DBName = "BDJocs";
        int port = 33333;

        server += ":" + port;
        server += "/" + DBName;
        server += "?useUnicode=true&characterEncoding=UTF-8";

        try {
//            conn = DriverManager.getConnection(connection);
            conn = DriverManager.getConnection(connection, user, pass);
            System.out.println("Connection stablished ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection conn) throws SQLException {

        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {

        try {
            if (this.conn != null) {
                return this.conn;
            } else {
                this.openConnection("jdbc:mysql://localhost", "root", "root");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return this.conn;
    }
        public void getSelect(String table) throws SQLException {
        try {
            ArrayList<JocBean> jocs = new ArrayList<JocBean>();
            ResultSet rst = this.getConnection().createStatement().executeQuery("SELECT * FROM " + table);
            System.out.println(table);
            System.out.println("******************************");

            while (rst.next()) {
                JocBean joc = new JocBean();

                joc.setPkid(rst.getInt(1));
                joc.setName(rst.getString(2));
                joc.setDescription(rst.getString(3));
                joc.setGenere(rst.getInt(4));

                jocs.add(joc);

            }
            for (JocBean joc : jocs) {
                System.out.println(joc.getPkid());
                System.out.println(joc.getName());
                System.out.println(joc.getDescription());
                System.out.println(joc.getGenere());
            }

        } catch (SQLException e) {

        }
    }

}