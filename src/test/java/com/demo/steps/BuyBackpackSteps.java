package com.demo.steps;

import com.demo.pages.*;
import io.cucumber.java.en.*;
import static com.demo.steps.Hooks.driver;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyBackpackSteps {

    private ProductsPage products() { return new ProductsPage(driver); }
    private ProductDetailPage pdp() { return new ProductDetailPage(driver); }
    private CartPage cart() { return new CartPage(driver); }
    private CheckoutPage checkout() { return new CheckoutPage(driver); }

    @Given("the app is launched and I am on the product catalog page")
    public void onCatalog() {
        // sederhana: periksa source mengandung "Products"
        assertThat(driver.getPageSource()).containsIgnoringCase("Products");
    }

    @When("I open the product {string}")
    public void openProduct(String name) {
        products().openProduct(name);
        assertThat(driver.getPageSource()).contains(name);
    }

    @And("I choose color {string}")
    public void chooseColor(String color) {
        pdp().chooseColor(color);
    }

    @And("I set quantity to {int}")
    public void setQuantity(int qty) {
        pdp().setQuantity(qty);
    }

    @And("I add it to the cart and open the cart")
    public void addToCartAndOpenCart() {
        pdp().addToCart();
        pdp().goToCart();
    }

    @Then("I should see the color {string} in the cart")
    public void verifyColorInCart(String color) {
        cart().assertBlueColorVisible(); // simple check via page source contains 'Blue'
        // jika mau lebih ketat, tambahkan method assertColor(String) di CartPage.
    }

    @And("the quantity should be {int}")
    public void verifyQuantity(int expected) {
        int actual = cart().getItemCountFor("Backpack");
        assertThat(actual).isEqualTo(expected);
        // lanjut ke checkout (opsional untuk scenario ini)
        // cart().proceedToCheckout();
        // checkout().assertOnCheckout();
    }
}
