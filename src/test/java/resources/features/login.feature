Feature: Login

  @RegressionTest @SmokeTest
  Scenario: Successful login with valid credentials

    Given Admin opens URL "login"
    And Admin enters Email as "admin"
    And Admin enters Password as "admin"
    And Admin click on Login
    Then Page Title should be "Dashboard"