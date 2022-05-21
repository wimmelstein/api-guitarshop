Feature: Everything guitars

  Scenario: Getting all guitars
    When I call the guitar endpoint with a valid token
    Then the result is a list of guitars of size 3