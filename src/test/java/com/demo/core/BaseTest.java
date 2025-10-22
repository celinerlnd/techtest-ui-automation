package com.demo.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        String appPath = Paths.get("src","test","resources","apps","mda-1.0.13-15.apk").toAbsolutePath().toString();

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator") // atau ganti ke nama device real
                .setApp(appPath)
                .setAppWaitActivity("*")           // tunggu activity apa pun
                .setAutoGrantPermissions(true);

        // Appium server default http://127.0.0.1:4723
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
