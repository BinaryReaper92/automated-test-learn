package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.NewTableModel;
import testObjects.DBTestTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTestSteps {
    private DBTestTest dbTestTest;
    private List<NewTableModel> dbResultSet;

    @Given("the database connection is established")
    public void theDatabaseConnectionIsEstablished() {
        dbTestTest = new DBTestTest();
    }

    @When("the user reads all items from the {string} table")
    public void theUserReadsAllItemsFromTheTable(String table) throws SQLException {
        dbResultSet = dbTestTest.readItems(table);
    }

    @Then("the items should be displayed on the console")
    public void theItemsShouldBeDisplayedOnTheConsole(){
        dbTestTest.PrintReadItems(dbResultSet);
    }

    @When("add a new item to {string} table with name {string} and value {int}")
    public void addANewItemToTableWithNameAndValue(String table, String name, int value) {
        dbTestTest.insertItem(table, name, value);
    }

    @Then("the new item should be added to the {string} table")
    public void theNewItemShouldBeAddedToTheTable(String table) {
        dbTestTest.isItemInserted(table);
    }

    @When("update item in {string} table with ID {int} with name is {string} and updated value is {int} data")
    public void updateItemInTableWithIDWithNameIsAndUpdatedValueIsData(String table, int itemId, String newName, int newValue) {
        dbTestTest.updateItem(table, itemId, newName, newValue);
    }

    @Then("the item in the {string} table should have the new values {string} and {int}")
    public void theItemInTheTableShouldHaveTheNewValuesAnd(String table, String expectedName, int expectedValue) {
        dbTestTest.isItemUpdated(table,expectedName, expectedValue);
    }

    @When("delete item from {string} table with ID {int}")
    public void deleteItemFromTableWithID(String table, int itemId) {
        dbTestTest.deleteItem(table, itemId);
    }

    @Then("the item should be removed from the {string} table")
    public void theItemShouldBeRemovedFromTheTable(String table) {
        dbTestTest.isItemDeleted(table);
    }
}
