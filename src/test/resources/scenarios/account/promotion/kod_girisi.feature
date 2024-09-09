@account @promotion @kodGirisi
Feature: Kod girisi cases

  @_userLogin @smoke
  Scenario: User should able to check promotions implementation
    Given user navigates to promotion page
    When user fills kod girisi component
    Then user verifies that return hesabim page after close promotion
