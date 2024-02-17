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


/**
 * A class that manages a specific database and provides a shell interface for interacting with it.
 */
class DatabaseManager {
    
    String server;
    String port;
    String user;
    String pass;
    String dbname;
    Scanner scanner;
    Connection dbConnection;

    /**
     * Default constructor with default values.
     */
    DatabaseManager() {
        server = null;
        port = null;
        user = null;
        pass = null;
        dbname = null;
        scanner = new Scanner(System.in);
        dbConnection = null;
    }

    /**
     * Constructor with custom database connection parameters.
     * @param server The server hostname.
     * @param port The port number.
     * @param user The username.
     * @param pass The password.
     * @param dbname The database name.
     */
    DatabaseManager(String server, String port, String user, String pass, String dbname) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;
    }

    /**
     * Establish a connection to the database using the provided parameters.
     * @return The established database connection.
     */
    public Connection connectDatabase() {
        try {
            String dbUrl = "jdbc:mysql://" + server + ":" + port + "/" + dbname;
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connection to the database successful.");
            return dbConnection;
        } catch (SQLException e) {
            System.err.println("Connection to the database failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Show the tables in the connected database.
     */
    public void showTables() {
        try {
            DatabaseMetaData metaData = dbConnection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[] {"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Execute a SELECT query and display the results.
     * @param query The SQL SELECT query to execute.
     */
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

    /**
     * Insert a new record into a specified table.
     * @param table The name of the table to insert the record into.
     */
    public void insertIntoTable(String table) {
        try {
            DatabaseMetaData metaData = dbConnection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, table, null);

            ArrayList<String> columnNames = new ArrayList<String>();
            while (columns.next()) {
                columnNames.add(columns.getString("COLUMN_NAME"));
            }

            StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
            queryBuilder.append(table);
            queryBuilder.append(" (");

            for (String columnName : columnNames) {
                queryBuilder.append(columnName);
                queryBuilder.append(", ");
            }

            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
            queryBuilder.append(") VALUES (");

            for (int i = 0; i < columnNames.size(); i++) {
                queryBuilder.append("?, ");
            }

            queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
            queryBuilder.append(")");

            String insertQuery = queryBuilder.toString();

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQuery);

            for (int i = 1; i <= columnNames.size(); i++) {
                System.out.print("Enter value for " + columnNames.get(i - 1) + ": ");
                String columnValue = scanner.nextLine();
                preparedStatement.setString(i, columnValue);
            }

            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted successfully.");

            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error inserting into the table: " + e.getMessage());
        }
    }

    /**
     * Show information about tables, keys, and foreign keys.
     * @param table The name of the table to describe.
     */
    public void showDescTable(String table) {
        // TO-DO: Implement this method to show table descriptions
        try {
            DatabaseMetaData metaData = dbConnection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, table, null);

            System.out.println("Description of table " + table + ":");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                System.out.println("Column: " + columnName + ", Type: " + dataType);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Start a shell interface for interacting with the connected database.
     */
    public void startShell() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.print("# (" + user + ") on " + server + ":" + port + "[" + dbname + "]> ");
            command = scanner.nextLine();

            String[] parts = command.split(" ");
            String action = parts[0];

            switch (action) {
                case "sh tables":
                    showTables();
                    break;
                case "describe":
                    if (parts.length > 1) {
                        String tableName = parts[1];
                        showDescTable(tableName);
                    } else {
                        System.out.println("Usage: describe <table_name>");
                    }
                    break;
                case "insert":
                    if (parts.length > 1) {
                        String tableName = parts[1];
                        insertIntoTable(tableName);
                    } else {
                        System.out.println("Usage: insert <table_name>");
                    }
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