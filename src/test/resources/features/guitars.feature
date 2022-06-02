Feature: Everything guitars

  Scenario: Getting all guitars
    Given I have a valid token for role "user"
    When I call the guitar endpoint
    Then the result is a list of guitars of size 3

  Scenario: Getting guitars with invalid token
    Given I have an invalid token
    When I call the guitar endpoint
    Then the result is a status of 403

  Scenario: Getting guitars with an expired token
    Given I have an expired token
    When I call the guitar endpoint
    Then the result is a status of 403

  Scenario: Posting guitar with user role
    Given I have a valid token for role "user"
    And I have a valid guitar object with brand "Fender" and model "Jazz" and price 1600
    When I make a post request to the guitar endpoint
    Then the result is a status of 403

  Scenario: Posting guitar with admin role
    Given I have a valid token for role "admin"
    And I have a valid guitar object with brand "Fender" and model "Jazz" and price 1600
    When I make a post request to the guitar endpoint
    Then the result is a status of 201
    And I validate the guitar object has an id greater than 1000003

  Scenario: Getting guitar without token
    Given I have no token
    When I call the guitar endpoint
    Then the result is a status of 403
