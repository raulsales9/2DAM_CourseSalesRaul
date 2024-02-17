import java.sql.Connection;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManageData {

    private Connection connection;

    public ManageData(String jdbcUrl, String usuario, String contraseña) {
        try {
            // Aquí debes establecer tu conexión a la base de datos
            this.connection = DriverManager.getConnection(jdbcUrl, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        try (Statement statement = connection.createStatement()) {
            // Aquí debes listar todas tus tablas
            String[] tablas = {"tabla1", "tabla2", "tabla3"};
            for (String tabla : tablas) {
                statement.executeUpdate("TRUNCATE TABLE " + tabla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillData(String script) {
        try {
            String sqlScript = new String(Files.readAllBytes(Paths.get(script)));
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sqlScript);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
