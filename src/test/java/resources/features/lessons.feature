Feature: Lessons

  Background:
    Given the admin is logged in
    And the admin is on lessons page with title "Lessons"

  @RegressionTest
  Scenario: Create a new lesson
    When admin clicks on the Add Lesson button
    Then the add new lesson form is opened with the "Base data" tab active
    And admin enters title as "Automated Test Lesson 2"
    And admin chooses Company as "! The new test"
    And admin chooses Subsidiary as "! new sub test"
    And admin chooses image
    And admin chooses Tag as "TEST"
    And admin switches lesson to "Public"
    And admin enters price as 100
    And admin changes Acceptance Rate to 50
    And admin enters Need to pass in day as 10
    And admin enters Lesson Type as "Test"
    And admin enters Description as "Automata test"
    And admin enters Requirements as "none"
    And admin clicks on add new Specific button
    Then Add New Specific Modal is displayed as "Add New Specific"
    And admin enters Key as "Test"
    And admin selects Type as "SHORTTEXT"
    And admin unchecks Add it in general block
    And admin clicks on Where is coming from the [KEY] of the [PRODUCT]? question
    And admin clicks on OK
    Then new Specific is added to the Lesson as "Test"
    And admin enters Specific name as "Test Specific"
    And admin clicks on Finish editing button
    Then Success modal should be displayed as "Success"
    And admin clicks on Success OK
