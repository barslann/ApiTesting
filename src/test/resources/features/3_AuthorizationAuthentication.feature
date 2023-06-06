Feature: Authentication and Authorization

  Scenario: Unauthorized access to deleteUserById endpoint
    Given a user with valid credentials
    When the user attempts to delete a user by id
    Then 3the response status code should be 401
    And the response body should contain the unauthorized error message
