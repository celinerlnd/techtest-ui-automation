package com.demo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:features",
        glue = {"com.demo.steps"},
        plugin = {"pretty","summary","html:target/cucumber-report.html"},
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    @Override @DataProvider(parallel = false)
    public Object[][] scenarios() { return super.scenarios(); }
}
