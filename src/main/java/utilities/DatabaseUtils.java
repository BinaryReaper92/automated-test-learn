package utilities;
import models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseUtils {

    public DatabaseUtils() {
    }

    public void readItems() {
        String query = "SELECT * FROM test_schema.new_table";
        try (DatabaseConnection dbConnection = new DatabaseConnection();
             Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int value = resultSet.getInt("value");

                System.out.printf("ID: %d, Name: %s, Value: %d%n", id, name, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertItem(String name, int value) {
        String query = "INSERT INTO `test_schema`.`new_table` (`name`, `value`) VALUES (?, ?)";
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
            e.printStackTrace();
        }
        return -1;
    }

    public void updateItem(int id, String newName, int newValue) {
        String query = "UPDATE `test_schema`.`new_table` SET name = ?, value = ? WHERE id = ?";
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
            e.printStackTrace();
        }
    }

    public void deleteItem(int id) {
        String query = "DELETE FROM `test_schema`.`new_table` WHERE id = ?";
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
            e.printStackTrace();
        }
    }
    public static Item getItemById(int id) {
        String query = "SELECT * FROM test_schema.new_table WHERE id = ?";
        Item item = null;
        ResultSet resultSet = null;

        try (DatabaseConnection dbConnection = new DatabaseConnection();
             Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("value")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return item;
    }
}
