Feature: Authentication

#  Happy Path - positive test case
  Scenario: Obtain authentication token
    Given the API base URL is "http://localhost:8080"
    And the credentials are "test2" and "test2"
    When a POST request is made to "/auth/login"
    Then the response body contains an authentication token

    # Negative test case
  Scenario: Obtain authentication token
    Given the API base URL is "http://localhost:8080"
    And the credentials are "test2" and "test8"
    When a POST request is made to "/auth/login" with wrong credentials
    Then Message should be "The username or password you entered is incorrect"


