package com.demo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final AndroidDriver driver;
    public LoginPage(AndroidDriver driver){ this.driver = driver; }

    public WebElement usernameField() {
        // Umumnya field ada label "Username" / hint "Username"
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\").instance(0)"));
    }

    public WebElement passwordField() {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\").instance(1)"));
    }

    public WebElement loginButton() {
        // Teks tombol biasanya "LOGIN" atau "Login"
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"Login\").className(\"android.widget.Button\")"));
    }

    public void login(String user, String pass){
        usernameField().click();
        usernameField().clear();
        usernameField().sendKeys(user);

        passwordField().click();
        passwordField().clear();
        passwordField().sendKeys(pass);

        loginButton().click();
    }
}
