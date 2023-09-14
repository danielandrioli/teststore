package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreHomePage extends AbstractPage {
    public static final String pageTitle = "teststore";

    @FindBy(css = "[title] .hidden-sm-down")
    private WebElement signInBtn;

    public StoreHomePage(WebDriver driver) {
        super(driver, pageTitle);
    }

}
