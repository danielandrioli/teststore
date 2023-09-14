package com.daniboy.pageobjects.store;

import com.daniboy.pageobjects.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreLoginPage extends AbstractPage {
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
        PageFactory.initElements(driver, this);

    }

    public void enterEmail(String email) {
        emailInputField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    public void clickSignInBtn() {
        signInBtn.click();
    }

    public void showOrHidePassword() {
        showPasswordBtn.click();
    }

    public Boolean isPasswordHidden() {
        if (passwordInputField.getAttribute("type").equalsIgnoreCase("password")) {
            return true;
        } else return false;
    }

    public void clearFields() {
        emailInputField.clear();
        passwordInputField.clear();
    }

    public String getFailedLoginMessage() {
        return failedLoginMessage.getText();
    }
}
