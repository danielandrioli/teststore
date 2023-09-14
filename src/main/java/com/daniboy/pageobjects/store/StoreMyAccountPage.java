package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreMyAccountPage extends AbstractPage {
    public static final String pageTitle = "My account";

    @FindBy(css = ".hidden-sm-down.logout")
    private WebElement signOutBtn;

    public StoreMyAccountPage(WebDriver driver) {
        super(driver, pageTitle);
        PageFactory.initElements(driver, this);

    }

    public void logout() {
        signOutBtn.click();
    }
}
