@account @personalInformation
Feature: Personal Information

  @_userLogin @smoke
  Scenario: User should able to see member id is display properly
    Given user get member id from dashboard
    When user navigates to bilgilerim page
    When user verifies that member id is display properly