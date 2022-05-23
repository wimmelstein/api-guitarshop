Feature: Everything guitars

  Scenario: Getting all guitars
    Given I have a valid token for role "user"
    When I call the guitar endpoint
    Then the result is a list of guitars of size 3

  Scenario: Getting guitars with invalid token
    Given I have an invalid token
    When I call the guitar endpoint
    Then the result is a status of 403