@cases
Feature: Cases Objects
  Scenario Outline: Validation of  Cases object in App Launcher
    Given User is on SingleSignon application Home Page
    When User click on app launcher
    And User Click on view all
    When we search for the cases objects in app launcher <CasesObject>
    Then cases objects results should display
    Examples: 
    | CasesObject |
    | Cases       |
    

  Scenario Outline: UI Validation of  Case home page
    Given User is on SingleSignon application Home Page
    When User click on app launcher
    And User Click on view all
    When we search for the cases objects in app launcher <CasesObject>
    Then cases objects results should display
    When click on cases object
    And Click on recently viewed
    Then verify cases list items
    Examples: 
    | CasesObject |
    | Cases       |