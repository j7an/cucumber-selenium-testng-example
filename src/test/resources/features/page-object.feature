Feature: Login Functionality

  Scenario: Successful login into the system
    Given the user is on the login page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks on the login button
    Then the user should be redirected to the dashboard page