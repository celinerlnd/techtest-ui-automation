package com.demo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProductDetailPage {
    private final AndroidDriver driver;
    public ProductDetailPage(AndroidDriver driver){ this.driver = driver; }

    public void chooseColor(String color) {
        String locator = color + " color"; // "Blue color"
        WebElement colorElement = driver.findElement(AppiumBy.accessibilityId(locator));
        colorElement.click();
    }

    public void setQuantity(int qty) {
        // Scroll ke tombol plus biar kelihatan
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/plusIV\"))"
        ));
        // Cari tombol plus
        WebElement plus = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/plusIV"));

        // Klik tombol plus sebanyak (qty - 1) kali
        for (int i = 1; i < qty; i++) {
            plus.click();
        }
    }


    public void addToCart() {
        // Scroll ke button (kalau butuh)
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/cartBt\"))"
        ));

        // Klik tombol Add to Cart
        WebElement addButton = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt"));
        addButton.click();
    }

    // checking cart validation count items
    public void assertCartItemCount(int expectedCount) {
        // Cari badge angka di icon cart
        WebElement badge = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartTV"));

        // Ambil teks dan ubah ke int
        String countText = badge.getText().trim();
        int actualCount = Integer.parseInt(countText);

        // Assertion manual
        if (actualCount != expectedCount) {
            throw new AssertionError(
                    "Expected cart count " + expectedCount + " but got " + actualCount
            );
        }
    }

    public void tapCartIcon() {
        // pakai ID (lebih stabil daripada xpath)
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartRL")).click();
    }

}
