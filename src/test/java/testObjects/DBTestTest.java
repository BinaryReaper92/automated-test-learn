package testObjects;

import models.Item;
import utilities.DatabaseUtils;

import static org.testng.AssertJUnit.assertTrue;

public class DBTestTest {
    private DatabaseUtils DatabaseUtils;
    private int latestItemId;
    private int deletedItemId;
    private int insertedItemId;

    public DBTestTest() {
        DatabaseUtils = new DatabaseUtils();
    }

    public void readItems() {
        DatabaseUtils.readItems();
    }

    public void insertItem(String name, int value) {
        latestItemId = DatabaseUtils.insertItem(name, value);
    }

    public void updateItem(int id, String newName, int newValue) {
        DatabaseUtils.updateItem(id, newName, newValue);
    }

    public void deleteItem(int id) {
        DatabaseUtils.deleteItem(id);
        deletedItemId = id;
    }

    public int getLatestItemId() {
        return latestItemId;
    }
    public void isItemInserted(){
        System.out.println(insertedItemId);
        Item item = DatabaseUtils.getItemById(insertedItemId);
    }

    public void isItemDeleted(){
        System.out.println(deletedItemId);
        Item item = DatabaseUtils.getItemById(deletedItemId);
        assertTrue("Item with ID " + deletedItemId + " should be removed from the table", item == null);




    }

}
