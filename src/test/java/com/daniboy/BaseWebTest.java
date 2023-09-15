package com.daniboy;

import com.daniboy.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(ITestListenerImpl.class)
public abstract class BaseWebTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {
        driver = DriverFactory.getDriverFromString(browser);
        driver.manage().window().maximize();

        System.out.println("setup");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
        System.out.println("teardown");
    }

    public WebDriver getDriver() {
        return driver;
    }
}

