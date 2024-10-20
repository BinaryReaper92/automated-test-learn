package utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {
    private Connection connection;

    public DatabaseConnection() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = ConfigReader.getDBUrl();
        String username = ConfigReader.getDBUser();
        String password = ConfigReader.getDBPassword();

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Log4j.error(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            Log4j.error(e.getMessage());
        }
    }
}
