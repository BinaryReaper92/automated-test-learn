package testObjects;

import models.ItemModel;
import models.NewTableModel;
import utilities.DatabaseUtils;

import java.sql.SQLException;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class DBTestTest {
    private int latestItemId;
    private int updatedItemId;
    private int deletedItemId;

    public List<NewTableModel> readItems() throws SQLException {
        return DatabaseUtils.readItems();
    }

    public void PrintReadItems(List<NewTableModel> newTableList){
        for(NewTableModel newTable : newTableList) {
            System.out.printf("ID: %d, Name: %s, Value: %d%n", newTable.id, newTable.name, newTable.value);
        }
    }

    public void insertItem(String name, int value) {
        latestItemId = DatabaseUtils.insertItem(name, value);
    }

    public void updateItem(int id, String newName, int newValue) {
        DatabaseUtils.updateItem(id, newName, newValue);
        updatedItemId = id;
    }

    public void deleteItem(int id) {
        DatabaseUtils.deleteItem(id);
        deletedItemId = id;
    }

    public void isItemInserted() {
        ItemModel itemModel = DatabaseUtils.getItemById(latestItemId);
        assertNotNull("Item with ID " + latestItemId + " should be in the table", itemModel);
    }

    public void isItemUpdated(String expectedName, int expectedValue) {
        ItemModel itemModel = DatabaseUtils.getItemById(updatedItemId);
        assertNotNull("Item with ID " + updatedItemId + " should be in the table", itemModel);
        assertEquals("Item name should be updated", expectedName, itemModel.getName());
        assertEquals("Item value should be updated", expectedValue, itemModel.getValue());
    }

    public void isItemDeleted() {
        ItemModel itemModel = DatabaseUtils.getItemById(deletedItemId);
        assertNull("Item with ID " + deletedItemId + " should be removed from the table", itemModel);
    }
}


