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

    When I tap the proceed to checkout button
    And I should be redirected to the Login page
    And I fill the login form with username "bod@example.com" and password "10203040"
    And I click login button
    Then I should be redirected to the Shipping page

    When I fill in shipping form with:
      | FullName       | Celine Rolinda  |
      | Address Line 1 | Jl Jakarta Raya |
      | Address Line 2 | Duren Sawit     |
      | City           | Jakarta Selatan |
      | State/Region   | DKI Jakarta     |
      | Zip Code       | 13850           |
      | Country        | Indonesia       |
    And I click on to payment button
    Then I should be redirected to the Payment page

    When I fill in payment method form
      | FullName       | Celine Rolinda  |
      | Card Number    | 390218401343242 |
      | Expiration Date| 03/25           |
      | Security Code  | 1349            |
    And I click on Review Order button
    Then I should be redirected to the Checkout Review your order page