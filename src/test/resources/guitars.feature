Feature: Everything guitars

  Scenario: Getting all guitars
    When I call the guitar endpoint with a valid token
    Then the result is a list of guitars of size 3

  Scenario: Getting guitars with invalid token
    When the guitar endpoint is called with an invalid token
    #Then the result is a status of 403