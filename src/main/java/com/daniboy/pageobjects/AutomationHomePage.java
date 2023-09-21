package com.daniboy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutomationHomePage extends BasePage {
    public static final String pageTitle = "Homepage";

    @FindBy(linkText = "TEST STORE")
    private WebElement testStore;
    @FindBy(css = ".close-cookie-warning > span")
    private WebElement closeCookie;

    public AutomationHomePage(WebDriver driver) {
        super(driver, pageTitle);
        closeCookie.click();
    }

    public void clickOnTestStore() {
        testStore.click();
    }
}
