package com.demo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.assertj.core.api.Assertions;

public class CheckoutPage {
    private final AndroidDriver driver;
    public CheckoutPage(AndroidDriver driver){ this.driver = driver; }

    public void assertOnCheckout(){
        String src = driver.getPageSource().toLowerCase();
        Assertions.assertThat(src).contains("checkout");
    }
}
