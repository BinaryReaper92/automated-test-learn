Feature: DBTest

  @DBTest
  Scenario: Read items from the table
    Given the database connection is established
    When the user reads all items from the table
    Then the items should be displayed on the console

  @DBTest
  Scenario: Insert a new item into the table
    Given the database connection is established
    When a new item with name "NewItem" and value 101
    Then the new item should be added to the table

  @DBTest
  Scenario: Update the value of an existing item in the table
    Given the database connection is established
    When update item with ID 106 with name is "UpdatedItem" and updated value is 200 data
    Then the item in the table should have the new values "UpdatedItem" and 200

  @DBTest
  Scenario: Delete an existing item from the table
    Given the database connection is established
    When delete item with ID 104
    Then the item should be removed from the table

