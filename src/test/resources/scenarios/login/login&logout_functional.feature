@login&logout
Feature: Nesine login & logout

  @smoke
  Scenario: User should be able to log in and logout from system
    Given user navigates to nesine login page
    When user log in nesine dashboard
    Then user verifies that logged in successfully
    And user log out from system
    Then user verifies that logged out successfully

  @smoke
  Scenario: User should not be able to log in with invalid credentials
    Given user navigates to nesine login page
    When user attempts to log in with invalid credentials
    And user should see an invalid credential dialog error details
