package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class StoreBasePage extends BasePage {

    @FindBy(css = ".hidden-sm-down.logout")
    protected WebElement signOutBtn;
    @FindBy(css = "[title] .hidden-sm-down")
    protected WebElement signInBtn;
    @FindBy(css = "div#_desktop_cart a[rel='nofollow']")
    protected WebElement cartBtn;

    public StoreBasePage(WebDriver driver, String pageTitle) {
        super(driver, pageTitle);
    }
}
