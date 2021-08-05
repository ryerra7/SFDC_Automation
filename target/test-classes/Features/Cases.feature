@cases
Feature: Cases Objects
  Scenario Outline: Cases object in App Launcher1
    Given User is on SingleSignon application Home Page
    When User click on app launcher
    And User Click on view all
    When we search for the cases objects in app launcher <CasesObject>
    Then cases objects results should display
    Examples: 
    | CasesObject |
    | Cases       |
    

  Scenario Outline: Cases object in App Launcher2
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