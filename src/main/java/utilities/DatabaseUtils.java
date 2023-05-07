package utilities;
import models.ItemModel;
import models.NewTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseUtils {

    public DatabaseUtils() {

    }

    public static List<NewTableModel> readItems(String table) throws SQLException {
        List<NewTableModel> result = new ArrayList<>();
        String query = "SELECT * FROM test_schema."+ table;
        try (DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                NewTableModel newTable = new NewTableModel();
                newTable.id = resultSet.getInt("id");
                newTable.name = resultSet.getString("name");
                newTable.value = resultSet.getInt("value");
                result.add(newTable);
            }
        } catch (SQLException e) {
            Log4j.error(e.getMessage());
            throw e;
        }
        return result;
    }

    public static int insertItem(String table, String name, int value) {
        String query = "INSERT INTO `test_schema`.`"+table+"` (`name`, `value`) VALUES (?, ?)";
        try (DatabaseConnection dbConnection = new DatabaseConnection();
             Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, value);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    System.out.printf("Inserted item with ID: %d%n", insertedId);
                    return insertedId;
                }
            }
        } catch (SQLException e) {
            Log4j.error(e.getMessage());
        }
        return -1;
    }

    public static void updateItem(String table, int id, String newName, int newValue) {
        String query = "UPDATE `test_schema`.`"+table+"` SET name = ?, value = ? WHERE id = ?";
        try (DatabaseConnection dbConnection = new DatabaseConnection();
             Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newValue);
            preparedStatement.setInt(3, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.printf("Updated item with ID: %d%n", id);
            } else {
                System.out.printf("No item found with ID: %d%n", id);
            }
        } catch (SQLException e) {
            Log4j.error(e.getMessage());
        }
    }

    public static void deleteItem(String table, int id) {
        String query = "DELETE FROM `test_schema`.`"+table+"` WHERE id = ?";
        try (DatabaseConnection dbConnection = new DatabaseConnection();
             Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.printf("Deleted item with ID: %d%n", id);
            } else {
                System.out.printf("No item found with ID: %d%n", id);
            }
        } catch (SQLException e) {
            Log4j.error(e.getMessage());
        }
    }
    public static ItemModel getItemById(String table, int id) {
        String query = "SELECT * FROM test_schema."+table+" WHERE id = ?";
        ItemModel itemModel = null;
        ResultSet resultSet = null;

        try (DatabaseConnection dbConnection = new DatabaseConnection();
             Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                itemModel = new ItemModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("value")
                );
            }
        } catch (SQLException e) {
            Log4j.error(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    Log4j.error(e.getMessage());
                }
            }
        }

        return itemModel;
    }


}
