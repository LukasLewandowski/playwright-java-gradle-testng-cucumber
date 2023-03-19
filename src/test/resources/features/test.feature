Feature: Example feature
  Scenario: Verify the title of the example website
    Given I navigate to "https://www.example.com"
    Then the title should be "Example Domain"
    Given I navigate to "https://www.google.com"
    Then wait for 2 seconds