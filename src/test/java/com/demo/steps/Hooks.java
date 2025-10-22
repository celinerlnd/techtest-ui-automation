package com.demo.steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class Hooks {
    public static AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        System.out.println("[HOOKS] Launching Appium session...");
        String appPath = Paths.get("src","test","resources","apps","mda-1.0.13-15.apk").toAbsolutePath().toString();

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Android Emulator")
                .setApp(appPath)
                .setAppWaitActivity("*")
                .setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        System.out.println("[HOOKS] ✅ sessionId = " + driver.getSessionId());
    }

    @After
    public void tearDown() {
        try {
            // beri waktu 10 detik buat kamu lihat app-nya
            Thread.sleep(10_000);
        } catch (InterruptedException ignored) {}
        System.out.println("[HOOKS] @After");
        if (driver != null) driver.quit();
    }
}
