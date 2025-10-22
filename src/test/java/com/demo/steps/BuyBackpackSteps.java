package com.demo.steps;

import com.demo.pages.*;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.*;

import java.util.Map;

import static com.demo.steps.Hooks.driver;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyBackpackSteps {

    private ProductsPage products() {
        return new ProductsPage(driver); }
    private ProductDetailPage pdp() {
        return new ProductDetailPage(driver); }
    private CartPage cart() {
        return new CartPage(driver); }
    private CheckoutPage checkout() {
        return new CheckoutPage(driver); }
    private ShippingPage shipping() {
        return new ShippingPage(driver);
    }

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

    @And("I fill the login form with username {string} and password {string}")
    public void fillLoginForm(String username, String password) {
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET")).sendKeys(username);
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET")).sendKeys(password);
    }

    @And("I click login button")
    public void clickLoginButton() {
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn")).click();
    }

    @Then("I should be redirected to the Shipping page")
    public void verifyRedirectToShippingPage() {
        boolean isDisplayed = driver.findElement(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/fullNameET")
        ).isDisplayed();

        assertThat(isDisplayed).isTrue();
    }

    @When("I fill in shipping form with:")
    public void fillShippingForm(Map<String, String> form) {
        shipping().fillForm(
                form.get("FullName"),
                form.get("Address Line 1"),
                form.get("Address Line 2"),
                form.get("City"),
                form.get("State/Region"),
                form.get("Zip Code"),
                form.get("Country")
        );
    }

    @And("I click on to payment button")
    public void clickOnToPaymentButton() {
        shipping().clickToPaymentButton();
    }

    @Then("I should be redirected to the Payment page")
    public void verifyRedirectToPaymentPage() {
        boolean isDisplayed = driver.findElement(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/cardNumberET")
        ).isDisplayed();

        assertThat(isDisplayed).isTrue();
    }
    @When("I fill in payment method form")
    public void fillPaymentForm(io.cucumber.datatable.DataTable table) {
        var m = table.asMap(String.class, String.class);
        payment().fillForm(
                m.get("FullName"),
                m.get("Card Number"),
                m.get("Expiration Date"),
                m.get("Security Code")
        );
    }

    @And("I click on Review Order button")
    public void clickReviewOrder() {
        payment().clickReviewOrder();
    }

    @Then("I should be redirected to the Checkout Review your order page")
    public void verifyOnReviewOrderPage() {
        // Simple check: cari teks "Review" di halaman review
        String src = driver.getPageSource().toLowerCase();
        org.assertj.core.api.Assertions.assertThat(src)
                .contains("review").contains("order");
    }

    // helper
    private com.demo.pages.PaymentPage payment() { return new com.demo.pages.PaymentPage(driver); }


}
