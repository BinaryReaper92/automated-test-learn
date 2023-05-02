Feature: Lessons

  Background:
    Given the admin is logged in
    And the admin is on lessons page with title "Lessons"

  @RegressionTest @SmokeTest
  Scenario: Create a new lesson
    When admin clicks on the Add Lesson button
    Then the add new lesson form is opened with the "Base data" tab active
    And admin enter title as "Automated Test Lesson 1"
    And admin choose Company as "! The new test"
    And admin choose Subsidiary as "! new sub test"
    And admin choose image
    And admin choose Tag as "TEST"
    And admin switch lesson to "Public"
    And admin enter price as 100
    And admin change Acceptance Rate to 50
    And admin enter Need to pass in day as 10
    And admin enter Lesson Type as "Test"
    And admin enter Description as "Automata test"
    And admin enter Requirements as "none"
    And admin click on add new Specific button
    Then Add New Specific Modal is displayed as "Add New Specific"
    And admin enter Key as "Test"
    And admin select Type as "SHORTTEXT"
    And admin uncheck Add it in general block
    And admin click on Where is coming from the [KEY] of the [PRODUCT]? question
    And admin click on OK
    Then new Specific is added to the Lesson as "Test"
    And admin enter Specific name as "Test Specific"
    And admin click on Finish editing button
    Then Success modal should be displayed as "Success"
    And admin click on Success OK
    And admin click on Finish editing button
