package com.daniboy.pageobjects.store;

import org.openqa.selenium.WebDriver;

public class StoreMyAccountPage extends StoreBasePage {
    public static final String pageTitle = "My account";
    private WebDriver driver;

    public StoreMyAccountPage(WebDriver driver) {
        super(driver, pageTitle);
        this.driver = driver;
    }

    public StoreLoginPage logout() {
        signOutBtn.click();
        return new StoreLoginPage(driver);
    }
}
