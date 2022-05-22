Feature: Login

  Scenario: Post request to /login will result in jwt token
    When the client calls /login with correct username/password
    Then the client receives a status of 200
    And the client receives a JWT-token
