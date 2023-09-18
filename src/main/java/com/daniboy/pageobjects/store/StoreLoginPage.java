package com.daniboy.pageobjects.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreLoginPage extends StoreBasePage {
    public static final String pageTitle = "Login";

    @FindBy(css = "section input[name='email']")
    private WebElement emailInputField;
    @FindBy(css = "input[name='password']")
    private WebElement passwordInputField;
    @FindBy(css = "button#submit-login")
    private WebElement signInBtn;
    @FindBy(css = "[type='button']")
    private WebElement showPasswordBtn;
    @FindBy(css = ".alert-danger")
    private WebElement failedLoginMessage;

    public StoreLoginPage(WebDriver driver) {
        super(driver, pageTitle);
    }

    public StoreLoginPage enterEmail(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    public StoreLoginPage enterPassword(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    public StoreLoginPage clickSignInBtn() {
        signInBtn.click();
        return this;
    }

    public StoreLoginPage showOrHidePassword() {
        showPasswordBtn.click();
        return this;
    }

    public Boolean isPasswordHidden() {
        if (passwordInputField.getAttribute("type").equalsIgnoreCase("password")) {
            return true;
        } else return false;
    }

    public StoreLoginPage clearFields() {
        emailInputField.clear();
        passwordInputField.clear();
        return this;
    }

    public String getFailedLoginMessage() {
        return failedLoginMessage.getText();
    }
}
