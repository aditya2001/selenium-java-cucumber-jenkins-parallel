
Feature: Perform different actions on the page

  Background:
    Given A web browser is at heroku app

  Scenario: 1. Close windows add popup
    When user clicks on Entry Add window popup
    And close window add popup
    Then click on re-enable button


  Scenario: 2. Click on multiple windows
    When user clicks on multiple windows button
    And user navigates to new window opened and print title
    Then navigate back to previous window









