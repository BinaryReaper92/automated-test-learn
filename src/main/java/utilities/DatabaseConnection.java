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
        String url = ConfigLoader.getDBUrl();
        String username = ConfigLoader.getDBUser();
        String password = ConfigLoader.getDBPassword();

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}
