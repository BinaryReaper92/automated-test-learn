Feature: DBTest

  @DBTest
  Scenario: Read items from the table
    Given the database connection is established
    When the user reads all items from the "new_table" table
    Then the items should be displayed on the console

  @DBTest
  Scenario: Insert a new item into the table
    Given the database connection is established
    When add a new item to "new_table" table with name "NewItem" and value 101
    Then the new item should be added to the "new_table" table

  @DBTest
  Scenario: Update the value of an existing item in the table
    Given the database connection is established
    When update item in "new_table" table with ID 107 with name is "UpdatedItem" and updated value is 200 data
    Then the item in the "new_table" table should have the new values "UpdatedItem" and 200

  @DBTest
  Scenario: Delete an existing item from the table
    Given the database connection is established
    When delete item from "new_table" table with ID 105
    Then the item should be removed from the "new_table" table

