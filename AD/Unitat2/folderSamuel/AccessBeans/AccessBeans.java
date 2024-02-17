/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ieseljust.AccessBeans;

import com.ieseljust.AccessBeans.Controller.ManageDB;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author samuel
 */
public class AccessBeans {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ManageDB DB = new ManageDB();
            Connection conn = DB.openConnection("localhost", "root", "root");
            DB.getSelect("Joc");

        } catch (SQLException E) {
            E.getMessage();
        }

    }
}
