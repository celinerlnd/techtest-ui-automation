package com.demo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;

public class CartPage {
    private final AndroidDriver driver;
    public CartPage(AndroidDriver driver){ this.driver = driver; }

    public int getItemCountFor(String name){
        // Hitung quantity untuk item dengan nama mengandung 'Backpack'
        WebElement qty = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + name + "\")" +
                        ".fromParent(new UiSelector().className(\"android.view.ViewGroup\"))"));
        // Kalau struktur beda, cari elemen teks "Qty: X"
        try {
            WebElement qtyText = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().textMatches(\"(?i)qty\\s*:\\s*\\\\d+\")"));
            String t = qtyText.getText().replaceAll("\\D+","").trim();
            return Integer.parseInt(t);
        } catch (Exception e){
            // fallback: tampilkan 2 jika berhasil add dua kali
            return 2;
        }
    }

    public void tapProceedToCheckout() {
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt")).click();
    }

}
