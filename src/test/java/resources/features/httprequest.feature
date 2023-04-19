Feature: HttpRequest

  @APITest
  Scenario: Successful login with valid credentials API

    Given Get URL from config
    When Calling the "api/auth/login/email" endpoint with the given body: "src/main/resources/requestsBodyJsons/login.json" while "logged out"
    Then Receiving 200

  @APITest
  Scenario: Unsuccessful lesson save with valid credentials API

    Given Get URL from config
    When Calling the "api/lessons" endpoint with the given body: "src/main/resources/requestsBodyJsons/wronglesson.json" while "logged in"
    Then Receiving 422

  @APITest
  Scenario: Get the details of all companies

    Given Get URL from config
    When Calling the "api/companies" endpoint while "logged in"
    Then Receiving 200

  @APITest
  Scenario: Change detail of Automated test lesson

    Given Get URL from config
    When Calling the "api/lessons/2317" endpoint with the given body: "src/main/resources/requestsBodyJsons/lesson2317.json"
    Then Receiving id 2317