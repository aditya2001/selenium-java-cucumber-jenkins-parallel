
Feature: Perform different actions on the page

  Background:
    Given A web browser is at heroku app


  Scenario: 1.Select drop down value
    When user clicks on drop down button
    And user select drop down value with
      | value    |
      | Option 1 |
    Then validate drop value displayed














