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


    public void addToCart(){
        WebElement add = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"Add to cart\").className(\"android.widget.Button\")"));
        add.click();
    }

    public void goToCart(){
        // Icon cart di header atau tombol "Cart"
        try {
            driver.findElement(AppiumBy.accessibilityId("cart")).click();
        } catch (Exception e) {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().descriptionContains(\"Cart\")")).click();
        }
    }
}
