package com.daniboy.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum DriverFactory {
    CHROME {
        @Override
        protected WebDriver getDriver() {
            return new ChromeDriver();
        }
    },
    EDGE {
        @Override
        protected WebDriver getDriver() {
            return new EdgeDriver();
        }
    },
    FIREFOX {
        @Override
        protected WebDriver getDriver() {
            return new FirefoxDriver();
        }
    };

    protected abstract WebDriver getDriver();

    public static WebDriver getDriverFromString(String browser) {
        for (DriverFactory dFac : DriverFactory.values()) {
            if (browser.equalsIgnoreCase(dFac.name())) {
                return dFac.getDriver();
            }
        }
        return DriverFactory.CHROME.getDriver();
    }
}
