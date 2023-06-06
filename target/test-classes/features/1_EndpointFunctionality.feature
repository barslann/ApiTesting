Feature: User Registration

  @Register
  Scenario: Successful user registration
    Given a user registration request with valid data
    When the request is made to the register endpoint
    Then the response status code should be 201
    And the response body should contain the registration details

#  Scenario: User registration with invalid data
#    Given a user registration request with invalid data
#    When the request is made to the /register endpoint
#    Then the response status code should be 417
#    And the response body should contain the error message
