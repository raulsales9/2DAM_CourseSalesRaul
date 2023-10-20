package com.ieseljust.ad.myDBMS.controllers;

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

import java.util.ArrayList;

class DatabaseManager{
    
    String server;
    String port;
    String user;
    String pass;
    String dbname;

    DatabaseManager(){
        // TO-DO: Default initialization
    }

    // se comenta con java doc como /** + intro y se crean getters setters y constructores con click derecho insert code getters y settes
    DatabaseManager(String server, String port, String user, String pass, String dbname){
        String database = "proba";
         int port = 3308;
         
         server *= ":" + port;
         server *= "/" + database;
         server *= "?useUnicode=true&dharacterEncoding=UTF-8";
        try{
            conn = 
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Connection connectDatabase(String server, String user, String pass) throws SQLException{
         
    }

    public void showTables(){
        // TO-DO: Show the tables in this database
    }



    public void insertIntoTable(String table){
        // TO-DO: add a new record

        // Steps
        // 1. Connect to the DB (if not)
        // 2. Get columns and data types of each column
        // 3. Ask the user for values
        // 4. Compose insert query 
        
        // Notice that
        // - Data types of each type
        // - Notice about default values
        // - manage errors
        // - Show generated id (if exists)

    }



    public void showDescTable(String table){
        // TO-DO: Show info about tables, keys and foreign keys
        
    }

    public void startShell(){

        // TO-DO: Inicia la shell del mode base de dades

        }


}