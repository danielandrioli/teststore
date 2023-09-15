package com.daniboy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver, String pageTitle) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        if (!driver.getTitle().equalsIgnoreCase(pageTitle)) {
            throw new IllegalStateException(("This is not \"%s\", " +
                    "current page is: \"%s\" - %s ").formatted(pageTitle, driver.getTitle(), driver.getCurrentUrl()));
        }
    }
}
