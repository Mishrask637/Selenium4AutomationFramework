Feature: Amazon search feature

  Scenario: Search iphone
    Given user is on amazon home page
    When user searches for an iphone
    Then user can find the required phone in results page
    When user clicks on product and adds to cart
    Then product is added to cart