Feature: Login

  @RegressionTest
  Scenario: Successful login with valid credentials

    Given Admin opens URL "login"
    And Admin enters existing valid Email
    And Admin enters existing valid Password
    And Admin click on Login
    Then Page Title should be "Dashboard"

  @RegressionTest @ErrorsTest
  Scenario: Unsuccessful login with both credentials missing
    Given Admin opens URL "login"
    And Admin leaves Email as blank
    And Admin leaves Password as blank
    And Admin click on Login
    Then Email and Password error message should display as "This field is required!"

  @RegressionTest @ErrorsTest
  Scenario: Unsuccessful login with email missing
    Given Admin opens URL "login"
    And Admin leaves Email as blank
    And Admin enters existing valid Password
    And Admin click on Login
    Then Email error message should display as "This field is required!"

  @RegressionTest @ErrorsTest
  Scenario: Unsuccessful login with password missing
    Given Admin opens URL "login"
    And Admin enters existing valid Email
    And Admin leaves Password as blank
    And Admin click on Login
    Then Password error message should display as "This field is required!"

  @RegressionTest @ErrorsTest
  Scenario: Unsuccessful login with wrong email format
    Given Admin opens URL "login"
    And Admin enters wrong Email
    And Admin enters existing valid Password
    And Admin click on Login
    Then Missing Email Error message should display as "Please provide valid email address!"

  @RegressionTest @ErrorsTest
  Scenario: Unsuccessful login with wrong password format
    Given Admin opens URL "login"
    And Admin enters existing valid Email
    And Admin enters wrong Password
    And Admin click on Login
    Then Error message should display as "Incorrect Email or Password!"

  @RegressionTest @ErrorsTest
  Scenario: Unsuccessful login with non-existent credentials
    Given Admin opens URL "login"
    And Admin enters non existing Email
    And Admin enters non existing Password
    And Admin click on Login
    Then Error message should display as "Incorrect Email or Password!"

  @RegressionTest @SmokeTest
  Scenario: Login Button color change on Hoover
    Given Admin opens URL "login"
    And Admin Hoover on Login button
    Then Login button's color should change to yellow

  @RegressionTest
  Scenario: Change hidden password characters to visible
    Given Admin opens URL "login"
    And Admin enters wrong Password
    And Admin click on Eye icon
    Then Password should be visible


  @RegressionTest
  Scenario: Open forgot password modal
    Given Admin opens URL "login"
    And Admin click on Forgot password
    Then Forgot password modal should appear