@ignore
Feature: Select calendar on the make my trip website

  Background:
    Given A web browser is at make my trip website


  Scenario: 1.Select drop down value
    When user closes login popup on make my trip website
    And user clicks on calendar button to open popup
    When user selects date and month with
    |   date                |
    |  "June 2024", "8"     |















