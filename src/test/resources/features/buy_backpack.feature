Feature:  As a shopper, Im trying to Buy product from catalog where the product is
  Sauce Lab Back Packs in blue for 2 quantity

  Background:
    Given the app is launched and I am on the product catalog page

  Scenario: Buy Sauce Lab Back Packs Blue with quantity 2
    When I open the product "Sauce Lab Back Packs"
    And I choose color "Blue"
    And I set quantity to 2
    And I add it to the cart
    Then I should see icon cart has 2 count item

    When I tap the cart icon
    Then I should see "Sauce Lab Back Packs" with quantity 2 in the cart
