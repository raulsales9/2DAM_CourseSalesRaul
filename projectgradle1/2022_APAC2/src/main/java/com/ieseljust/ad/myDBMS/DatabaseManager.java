package com.ieseljust.ad.myDBMS;

import com.ieseljust.ad.myDBMS.*;
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
import java.util.Scanner;
import java.util.ArrayList;

class DatabaseManager{
    
    String server;
    String port;
    String user;
    String pass;
    String dbname;
    Scanner scanner;
    Connection dbConnection;

    DatabaseManager(){
        // TO-DO: Default initialization
        String server;
        String port;
        String user;
        String pass;
        String dbname;
        scanner = new Scanner(System.in);
        Connection dbConnection;
    }

    // se comenta con java doc como /** + intro y se crean getters setters y constructores con click derecho insert code getters y settes
    DatabaseManager(String server, String port, String user, String pass, String dbname){
       this.server = server;
       this.port = port;
       this.user = user;
       this.pass = pass;
       this.dbname = dbname;
    }

    public Connection connectDatabase(){
         try{
            String dbUrl = "jdbc:mysql://" + server + ":" + port + "/" + dbname;
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Conexión a la base de datos exitosa.");
            return dbConnection;
        }catch(SQLException e){
            System.err.println("Connection to DMBS failed" + e.getMessage());
            return null;
        }
    }
    

    public void showTables(){
        // TO-DO: Show the tables in this database
        try{
            DatabaseMetaData metaDatas = dbConnection.getMetaData();
            ResultSet tables = metaDatas.getTables(null, null, null, new String[] {"TABLE"});
            while(tables.next()){
                String columnName = tables.getString("TABLE_NAME");
                System.out.println(columnName);
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public void executeSelect(String query) {
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
    
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
    
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
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
        try {
            // Obtener metadatos de la tabla para conocer los nombres de las columnas
            DatabaseMetaData metaData = dbConnection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, table, null);
    
            ArrayList<String> columnNames = new ArrayList<String>();
            while (columns.next()) {
                columnNames.add(columns.getString("COLUMN_NAME"));
            }
    
            // Componer la consulta SQL de inserción
            StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
            queryBuilder.append(table); // Aquí usamos el nombre de la tabla que recibimos como parámetro
            queryBuilder.append(" (");
    
            for (String columnName : columnNames) {
                queryBuilder.append(columnName);
                queryBuilder.append(", ");
            }
    
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length()); // Eliminar la última coma y espacio
            queryBuilder.append(") VALUES (");
    
            for (int i = 0; i < columnNames.size(); i++) {
                queryBuilder.append("?, ");
            }
    
            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length()); // Eliminar la última coma y espacio
            queryBuilder.append(")");
    
            String insertQuery = queryBuilder.toString();
    
            // Preparar una consulta preparada para la inserción
            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQuery);
    
            // Solicitar valores de entrada al usuario para cada columna
            for (int i = 1; i <= columnNames.size(); i++) {
                System.out.print("Enter value for " + columnNames.get(i - 1) + ": ");
                String columnValue = scanner.nextLine();
                preparedStatement.setString(i, columnValue);
            }
    
            // Ejecutar la inserción
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted successfully.");
    
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error inserting into the table: " + e.getMessage());
        }
    }
    



    public void showDescTable(String table){
        // TO-DO: Show info about tables, keys and foreign keys
        try{
            DatabaseMetaData metaDatas = dbConnection.getMetaData();
            ResultSet colums = metaDatas.getTables(null, null, table, new String[] {"TABLE"});
            System.out.println("Descripcion" + table);
            while(colums.next()){
                
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    /*
     * I was initialize de scanner to get the inputs of the user, and control the instruccions with a do-while, when if the user type quit, the startshell finish his act.
     * The method and his funcionalities, is working as a callcenter to execute the instruccions
     */
    public void startShell(){
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.print("# (" + user + ") on " + server + ":" + port + "[" + dbname + "]> ");
            command = scanner.nextLine();

            switch (command) {
                case "sh tables":
                    showTables();
                    break;
                case "describe":
                    showDescTable(command);
                    break;
                case "insert":
                    insertIntoTable(command);
                    break;
                case "sql":
                    System.out.print("Enter SQL query: ");
                    String sqlQuery = scanner.nextLine();
                    executeSelect(sqlQuery);
                    break;
                case "quit":
                    System.out.println("Exiting database mode.");
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        } while (!command.equals("quit"));
    }


}