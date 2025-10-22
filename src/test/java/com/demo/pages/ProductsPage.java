package com.demo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductsPage {
    private final AndroidDriver driver;
    public ProductsPage(AndroidDriver driver){ this.driver = driver; }

    public void openProduct(String name){
        // Scroll biar produk kelihatan di layar
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"" + name + "\")"
        ));

        // Klik langsung card ViewGroup berdasarkan index (misalnya Backpack ada di urutan pertama)
        WebElement card = driver.findElement(By.xpath(
                "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup[1]"
        ));
        card.click();

        // Tunggu sebentar agar PDP sempat terbuka
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }
}
