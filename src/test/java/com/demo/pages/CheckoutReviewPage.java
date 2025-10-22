package com.demo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CheckoutReviewPage {
    private final AndroidDriver driver;
    public CheckoutReviewPage(AndroidDriver driver){ this.driver = driver; }

    public void fillForm(String fullName, String cardNumber, String exp, String cvv) {
        typeWithScroll("com.saucelabs.mydemoapp.android:id/nameET", fullName);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/cardNumberET", cardNumber);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/expirationDateET", exp);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/securityCodeET", cvv);
    }

    public void clickReviewOrder() {
        // Button id dari kamu: paymentBtn
        scrollIntoViewById("com.saucelabs.mydemoapp.android:id/paymentBtn");
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn")).click();
    }

    // --- helpers sederhana ---
    private void typeWithScroll(String id, String text){
        scrollIntoViewById(id);
        WebElement field = driver.findElement(AppiumBy.id(id));
        field.clear();
        field.sendKeys(text);
    }

    private void scrollIntoViewById(String id){
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"" + id + "\"))"
            ));
        } catch (Exception ignored) {}
    }
    public void clickPlaceOrder() {
        scrollIntoViewById("com.saucelabs.mydemoapp.android:id/paymentBtn");
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn")).click();
    }

}
