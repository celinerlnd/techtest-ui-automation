package com.demo.steps;

import com.demo.pages.*;
import io.appium.java_client.AppiumBy;
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

    @And("I add it to the cart")
    public void addToCart() {
        pdp().addToCart();
    }

    @Then("I should see icon cart has {int} count item")
    public void verifyCartCount(int expectedCount) {
        pdp().assertCartItemCount(expectedCount);
    }

    @And("the quantity should be {int}")
    public void verifyQuantity(int expected) {
        int actual = cart().getItemCountFor("Backpack");
        assertThat(actual).isEqualTo(expected);
        // lanjut ke checkout (opsional untuk scenario ini)
        // cart().proceedToCheckout();
        // checkout().assertOnCheckout();
    }

    @When("I tap the cart icon")
    public void tapCartIcon() {
        pdp().tapCartIcon(); // panggil method dari ProductDetailPage
    }

    @Then("I should see {string} with quantity {int} in the cart")
    public void assertItemInCart(String productName, int expectedQty) {
        int actualQty = cart().getItemCountFor(productName);
        org.assertj.core.api.Assertions.assertThat(actualQty)
                .as("Quantity in cart should match expected value")
                .isEqualTo(expectedQty);
    }

    @When("I tap the proceed to checkout button")
    public void tapProceedToCheckoutButton() {
        cart().tapProceedToCheckout();
    }

    @Then("I should be redirected to the Login page")
    public void verifyRedirectToLoginPage() {
        boolean isDisplayed = driver.findElement(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/usernameTV")
        ).isDisplayed();

        assertThat(isDisplayed).isTrue();
    }


}
