
package segonExamen.controllers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author pc-raul
 */

public class ManageDBMetaData {
    
    String server;
    int port;
    String user;
    String pass;
    String dbname;
    Connection dbConnection;

    public ManageDBMetaData(String server, int port, String user, String pass, String dbname) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;
        this.dbConnection = null;
    }

    public Connection connectDatabase() {
        try {
            String dbUrl = "jdbc:mysql://" + server + ":" + port + "/" + dbname;
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Conexión a la base de datos exitosa.");
            return dbConnection;
        } catch (SQLException e) {
            System.err.println("La conexión a la base de datos falló: " + e.getMessage());
            return null;
        }
    }

        public static void getTables(Connection connection) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "%", null);
        System.out.println("Tablas en la base de datos:");
        while (tables.next()) {
            System.out.println(tables.getString("TABLE_NAME"));
        }
    }

    public void getTablesInfo(String table) throws SQLException {
        if (table.equalsIgnoreCase("all") || table.equalsIgnoreCase("a")) {
            getTables(dbConnection);
        } else {
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM " + table);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                System.out.println("Información de la tabla " + table + ":");

                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metaData.getColumnName(i) + " " + metaData.getColumnTypeName(i));
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener información de la tabla " + table + ": " + e.getMessage());
            }
        }
    }
}
