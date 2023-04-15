Feature: HttpRequest

  @APITest
  Scenario: Successful login with valid credentials API

    Given Get URL from config
    When Calling the "api/auth/login/email" endpoint with the given body: "src/main/resources/requestsBodyJsons/login.json" while "logged out"
    Then Receiving 200

  @APITest
  Scenario: Successful login with valid credentials API

    Given Get URL from config
    When Calling the "api/lessons" endpoint with the given body: "src/main/resources/requestsBodyJsons/lessons.json" while "logged in"
    Then Receiving 500