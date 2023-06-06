Feature: Product Data Manipulation

  Scenario: Create a new product
    Given a product creation request with valid data
    When the request is made to the /addProduct endpoint
    Then 4the response status code should be 201
    And the response body should contain the created product details
    Given a valid product ID
    When the request is made to the productID endpoint
    Then 4the response status code should be 302
    And the response body should contain the product details

#  Scenario: Get product by ID
#    Given a valid product ID
#    When the request is made to the productID endpoint
#    Then 4the response status code should be 302
#    And the response body should contain the product details
#
#  Scenario: Update an existing product
#    Given an existing product ID and updated data
#    When the request is made to the /updateProduct/{productId} endpoint
#    Then 4the response status code should be 200
#    And the response body should contain the success message
#
#  Scenario: Delete an existing product
#    Given an existing product ID
#    When the request is made to the /deleteProduct/{productId} endpoint
#    Then 4the response status code should be 200
#    And 4the response body should contain the success message
