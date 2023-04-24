package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testObjects.DBTestTest;

public class DBTestSteps {
    private DBTestTest dbTestTest;

    @Given("the database connection is established")
    public void theDatabaseConnectionIsEstablished() {
        dbTestTest = new DBTestTest();
    }

    @When("the user reads all items from the table")
    public void theUserReadsAllItemsFromTheTable() {
        dbTestTest.readItems();
    }

    @Then("the items should be displayed on the console")
    public void theItemsShouldBeDisplayedOnTheConsole() {
        //readItems console
    }

    @When("a new item with name {string} and value {int}")
    public void aNewItemWithNameAndValue(String name, int value) {
        dbTestTest.insertItem(name, value);
    }

    @Then("the new item should be added to the table")
    public void theNewItemShouldBeAddedToTheTable() {
        dbTestTest.isItemInserted();
    }

    @When("update item with ID {int} with name is {string} and updated value is {int} data")
    public void updateItemWithIDWithNameIsAndUpdatedValueIsData(int itemId, String newName, int newValue) {
        dbTestTest.updateItem(itemId, newName, newValue);
    }

    @Then("the item in the table should have the new values {string} and {int}")
    public void theItemInTheTableShouldHaveTheNewValues(String expectedName, int expectedValue) {
        dbTestTest.isItemUpdated(expectedName, expectedValue);
    }

    @When("delete item with ID {int}")
    public void deleteItemWithID(int itemId) {
        dbTestTest.deleteItem(itemId);
    }

    @Then("the item should be removed from the table")
    public void theItemShouldBeRemovedFromTheTable() {
        dbTestTest.isItemDeleted();

    }


}
