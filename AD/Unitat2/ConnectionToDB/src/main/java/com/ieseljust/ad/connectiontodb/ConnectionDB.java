package com.ieseljust.ad.connectiontodb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc-raul
 */
class ManageDB {

    private Connection conn = null;

    String connection = "jdbc:mysql://localhost:3308/DBJoc?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
    
    
    
    public Connection openConnection(String server, String user, String pass) throws SQLException {

        String DBName = "BDJocs";
        int port = 3308;

        server += ":" + port;
        server += "/" + DBName;
        server += "?useUnicode=true&characterEncoding=UTF-8";

        try {
//            conn = DriverManager.getConnection(connection);
            conn = DriverManager.getConnection(server, user, pass);
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
            ResultSet rst = this.getConnection().createStatement().executeQuery("SELECT * FROM " + table);
            System.out.println("Content of " + table);
            System.out.println("******************************");
            ResultSetMetaData rsmdQuery = rst.getMetaData();
//            System.out.println(rsmdQuery);
            // print the columns name
//            for (int i = 1; i <= rsmdQuery.getColumnCount(); i++) {
//                System.out.print(String.format("%-25.25s", rsmdQuery.getColumnName(i)));
//            }

            while (rst.next()) {
                for (int i = 1; i <= rsmdQuery.getColumnCount(); i++) {
                    System.out.print(String.format("%-25.25s ", rst.getString(i)));
                }
                System.out.println();
            }

        } catch (SQLException e) {

        }
    }

    public void getDBTables() throws SQLException {
        DatabaseMetaData dbmd = this.getConnection().getMetaData();
        ResultSet rsmd = dbmd.getTables("DBJoc", null, null, null);
        while (rsmd.next()) {
            System.out.println(String.format("%-15s %-15s %-15s",
                    rsmd.getString(1),
                    rsmd.getString(3),
                    rsmd.getString(4)));
        }

    }

//    public int insert() throws SQLException {
//        int rows = 0;
//        String SQL
//                = "INSERT INTO Joc VALUES (1, 'Double Dragon', 'Dos germans bessons experts en "
//                + "arts marcials s`han de fer camí en un escenari urbà on membres "
//                + "de bandes rivals volen deixar - los fora de combat.', 1);";
//        int gameid=2;
//        String gameName = "Space invaders";
//        String gameDescription = "";
//        int gameGenere = 2;
//        String SQL="INSERT INTO Joc Values("+gameid+", '"+gameName+"', '"+gameDescription+"', "+gameGenere+");";
//        Statement query = this.getConnection().createStatement();
//        rows = query.executeUpdate(SQL);
//        
//        System.out.println("Number of rows affected: " + rows);
//        
//        return rows;
//    }
        public int insertStatementPrepared(int gameId,String gameName,String gameDescription,int gameGenere) throws SQLException {
        int rows = 0;
//        String SQL
//                = "INSERT INTO Joc VALUES (1, 'Double Dragon', 'Dos germans bessons experts en "
//                + "arts marcials s`han de fer camí en un escenari urbà on membres "
//                + "de bandes rivals volen deixar - los fora de combat.', 1);";
        //int gameid=2;
        //String gameName = "Space invaders";
        //String gameDescription = "";
        //int gameGenere = 2;
        String SQL="INSERT INTO Joc Values(?,?,?,?);";
        PreparedStatement pst = this.getConnection().prepareStatement(SQL);
        
        pst.setInt(1,gameId);
        pst.setString(2,gameName);
        pst.setString(3,gameDescription);
        pst.setInt(4,gameGenere);
        
        int rst = pst.executeUpdate();
        
        System.out.println("Number of rows affected: " + rows);
        
        return rows;
    }
}

public class ConnectionDB {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("ConnectionDB ");
        Connection conn = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        ManageDB DB = new ManageDB();
        DB.openConnection("jdbc:mysql://localhost", "root", "root");
//        DB.getSelect("Genere");
//        DB.getDBTables();
//        DB.insertStatementPrepared(Integer.parseInt(args[0]),args[1],args[2],Integer.parseInt(args[3]));
        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.println("TYPE_FORWARD_ONLY: " + dbmd.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
        System.out.println("TYPE_SCROLL_INSENSITIVE: " + dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
        System.out.println("TYPE_SCROLL_SENSITIVE: " + dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
        System.out.println("CONCUR_READ_ONLY: " + dbmd.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY));
        System.out.println("CONCUR_UPDATABLE: " + dbmd.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE));

//        conn = DB.getConnection();
//        Connection conn = DriverManager.getConnection(connection);
    }

}