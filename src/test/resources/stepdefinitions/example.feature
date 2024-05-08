Feature: Google Search on Grid

  Scenario: Searching for 'Cucumber'
    Given I open Google homepage
    When I search for 'Cucumber'
    Then I should see 'Cucumber' in the search results
