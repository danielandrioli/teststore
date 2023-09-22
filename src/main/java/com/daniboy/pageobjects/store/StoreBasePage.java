package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class StoreBasePage extends BasePage {

    @FindBy(css = ".hidden-sm-down.logout")
    protected WebElement signOutBtn;
    @FindBy(css = "[title] .hidden-sm-down")
    protected WebElement signInBtn;
    @FindBy(css = "div#_desktop_cart a[rel='nofollow']")
    protected WebElement cartBtn;
    @FindBy(css = "[title] .hidden-sm-down")
    protected WebElement myAccountBtn;
    @FindBy(css = ".cart-products-count")
    protected WebElement cartProductsCount;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public StoreBasePage(WebDriver driver, String pageTitle) {
        super(driver, pageTitle);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public StoreCartPage clickOnCartBtn() {
        return new StoreCartPage(driver);
    }

    public int getCartProductsCount() {
        wait.until(ExpectedConditions.visibilityOf(cartProductsCount));
        return Integer.parseInt(cartProductsCount.getText().replace("(", "").replace(")", ""));
    }

    // Outros métodos que podem ser criados: clickOnMyAccount, clickOnLogout...
    // Ambos devem retornar void.
    // Dependendo de onde o logout será clicado, o usuário poderá permanecer na mesma página ou parar na página de login.
    // E dependendo se o usuário está logado ou não, o signIn ou signOut vai aparecer.

    //  //div[@class='user-info']//a[@href='http://teststore.automationtesting.co.uk/my-account'] para tanto no signIn quanto no my account.
}
