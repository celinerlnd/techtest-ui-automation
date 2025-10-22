package com.demo.steps;

import io.cucumber.java.en.Given;
import static com.demo.steps.Hooks.driver;
import static org.assertj.core.api.Assertions.assertThat;

public class SanitySteps {
    @Given("the app is launched")
    public void the_app_is_launched() {
        assertThat(driver).as("Driver must be initialized by @Before Hooks").isNotNull();
        // akses session untuk “memaksa” app terbuka
        String src = driver.getPageSource();
        assertThat(src).isNotEmpty();
    }
}
