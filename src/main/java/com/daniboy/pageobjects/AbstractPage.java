package com.daniboy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    WebDriver driver;

    public AbstractPage(WebDriver driver, String pageTitle) {
//        PageFactory.initElements(driver, this); //initElements não sei se vai dar certo. Talvez com um lazy.
        this.driver = driver;
        if (!driver.getTitle().equals(pageTitle)) {
            throw new IllegalStateException(("This is not %s, " +
                    "current page is: %s - %s ").formatted(pageTitle, driver.getTitle(), driver.getCurrentUrl()));
        }
    }
}
