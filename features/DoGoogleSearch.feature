Feature: Do search on google
 In order search some keywords
 As a student
 I want to learn by search on google

 Scenario Outline: Do search on google
  Given I am on google home page
  When I do a search for keyword <keyword>
  Then I can see the search result page for <keyword>

Examples:
    |keyword|
    |cucumber|
    |cuke4duke|
