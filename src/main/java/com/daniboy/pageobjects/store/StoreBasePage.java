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
    @FindBy(css = "[title] .hidden-sm-down")
    protected WebElement myAccountBtn;

    private WebDriver driver;

    public StoreBasePage(WebDriver driver, String pageTitle) {
        super(driver, pageTitle);
        this.driver = driver;
    }

    public StoreCartPage clickOnCartBtn() {
        return new StoreCartPage(driver);
    }

    public void clickOnMyAccountBtn() {
        if (myAccountBtn.getText().equalsIgnoreCase("Sign in")) {
            //vai ter a página de login. Mas vai ter alguma diferença nesse método, afinal?
            //todo
        }
    }

    //métodos clicar no cart e retornar StoreShoppingCartPage

}
