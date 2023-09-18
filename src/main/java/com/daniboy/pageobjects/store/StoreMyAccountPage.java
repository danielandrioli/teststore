package com.daniboy.pageobjects.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreMyAccountPage extends StoreBasePage {
    public static final String pageTitle = "My account";

    public StoreMyAccountPage(WebDriver driver) {
        super(driver, pageTitle);
    }

    public StoreMyAccountPage logout() {
        signOutBtn.click();
        return this;
    }
}
