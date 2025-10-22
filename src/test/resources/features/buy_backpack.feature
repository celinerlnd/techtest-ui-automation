Feature:  As a shopper, Im trying to Buy product from catalog where the product is
  Sauce Lab Back Packs in blue for 2 quantity

  Background:
    Given the app is launched and I am on the product catalog page

  Scenario: Buy Sauce Lab Back Packs Blue with quantity 2
    When I open the product "Sauce Lab Back Packs"
    And I choose color "Blue"
    And I set quantity to 2
    And I add it to the cart button by clicking "Add to Cart"
#    Then I should see the color "Blue" in the cart
#    And the quantity should be 2
