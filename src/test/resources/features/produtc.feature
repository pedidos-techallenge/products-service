Feature: Product Management

  Scenario: List all products
    Given I have a list of products
    When I request all products
    Then I should get a non-empty list
