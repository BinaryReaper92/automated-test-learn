Feature: Lessons

  Background:
    Given the admin is logged in
    And the admin is on lessons page with title "Lessons"

  @RegressionTest @SmokeTest
  Scenario: Create a new lesson
    When admin clicks on the Add Lesson button
    Then the add new lesson form is opened with the "Base data" tab active
