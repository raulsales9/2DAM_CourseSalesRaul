import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JSONToDatabase {
    public static void main(String[] args) {
        // Replace with the actual JSON file path
        String jsonFilePath = "data.json";

        // Initialize the ConnectionManager with your database connection parameters
        ConnectionManager connectionManager = new ConnectionManager("your_db_server", "your_db_port", "your_db_user", "your_db_password");
        Connection dbConnection = connectionManager.connectDBMS();

        if (dbConnection != null) {
            try {
                // Read and parse the JSON data
                String jsonData = readFile(jsonFilePath);
                JSONArray jsonArray = new JSONArray(jsonData);

                // Create the necessary tables if they don't exist
                createTables(dbConnection);

                // Insert data from JSON into the database
                insertData(dbConnection, jsonArray);

                // Perform updates and deletions as needed
                // You can use SQL UPDATE and DELETE statements for this.

                // Close the database connection
                connectionManager.disconnectDBMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Read the JSON file and return its content as a string
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }

    // Create tables in the database if they don't exist
    private static void createTables(Connection dbConnection) {
        try {
            Statement statement = dbConnection.createStatement();

            // Create a sample table if it doesn't exist (replace with your actual table schema)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS sample_table (id INT PRIMARY KEY, name VARCHAR(255))";
            statement.execute(createTableSQL);

            // Add more tables as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert data from the JSON array into the database
    private static void insertData(Connection dbConnection, JSONArray jsonArray) throws SQLException {
        Statement statement = dbConnection.createStatement();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id"); // Adjust to match your JSON structure
            String name = jsonObject.getString("name"); // Adjust to match your JSON structure

            // Insert data into the sample_table (replace with your actual table name)
            String insertSQL = "INSERT INTO sample_table (id, name) VALUES (" + id + ", '" + name + "')";
            statement.execute(insertSQL);
        }
    }
}
