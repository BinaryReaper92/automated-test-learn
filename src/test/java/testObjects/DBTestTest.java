package testObjects;

import models.Item;
import utilities.DatabaseUtils;

import static org.testng.AssertJUnit.*;

public class DBTestTest {
    private DatabaseUtils databaseUtils;
    private int latestItemId;
    private int updatedItemId;
    private int deletedItemId;

    public DBTestTest() {
        databaseUtils = new DatabaseUtils();
    }

    public void readItems() {
        databaseUtils.readItems();
    }

    public void insertItem(String name, int value) {
        latestItemId = databaseUtils.insertItem(name, value);
    }

    public void updateItem(int id, String newName, int newValue) {
        databaseUtils.updateItem(id, newName, newValue);
        updatedItemId = id;
    }

    public void deleteItem(int id) {
        databaseUtils.deleteItem(id);
        deletedItemId = id;
    }

    public int getLatestItemId() {
        return latestItemId;
    }

    public void isItemInserted() {
        Item item = DatabaseUtils.getItemById(latestItemId);
        assertNotNull("Item with ID " + latestItemId + " should be in the table", item);
    }

    public void isItemUpdated(String expectedName, int expectedValue) {
        Item item = DatabaseUtils.getItemById(updatedItemId);
        assertNotNull("Item with ID " + updatedItemId + " should be in the table", item);
        assertEquals("Item name should be updated", expectedName, item.getName());
        assertEquals("Item value should be updated", expectedValue, item.getValue());
    }

    public void isItemDeleted() {
        Item item = DatabaseUtils.getItemById(deletedItemId);
        assertNull("Item with ID " + deletedItemId + " should be removed from the table", item);
    }
}


