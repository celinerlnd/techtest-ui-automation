package com.demo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class ShippingPage {
    private final AndroidDriver driver;
    public ShippingPage(AndroidDriver driver){ this.driver = driver; }

    public void fillForm(String fullName, String addr1, String addr2,
                         String city, String state, String zip, String country) {

        // helper: scroll lalu ketik
        typeWithScroll("com.saucelabs.mydemoapp.android:id/fullNameET", fullName);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/address1ET", addr1);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/address2ET", addr2);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/cityET", city);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/stateET", state);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/zipET", zip);
        typeWithScroll("com.saucelabs.mydemoapp.android:id/countryET", country);
    }

    private void typeWithScroll(String id, String text){
        // scroll ke field (kalau perlu)
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"" + id + "\"))"
            ));
        } catch (Exception ignored) {}
        // ketik
        WebElement field = driver.findElement(AppiumBy.id(id));
        field.clear();
        field.sendKeys(text);
    }
    public void clickToPaymentButton() {
        // scroll biar tombol kelihatan
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/paymentBtn\"))"
            ));
        } catch (Exception ignored) {}

        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn")).click();
    }

}
