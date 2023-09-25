package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreLoginPage;
import com.daniboy.pageobjects.store.StoreMyAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.daniboy.util.Constants.failedLoginMessage;
import static com.daniboy.util.Constants.storeTestSiteBaseURL;

public class LoginTest extends BaseWebTest {

    @BeforeMethod
    public void enterUrl() {
        driver.manage().deleteAllCookies();
        driver.get(storeTestSiteBaseURL + "login?back=my-account");
    }

    //S1T1
    @Parameters({"email", "password"})
    @Test(description = "Given I'm on login page, when I submit valid credentials, then I should go to my account page." +
            "Given I'm logged and I'm on My acccount page, when I click on logout, then I should go to the login page.")
    public void loginWithValidCredentialsThenLogout(String email, String password) {
        StoreMyAccountPage myAccountPage = new StoreLoginPage(driver)
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInBtn();
        Assert.assertEquals(driver.getTitle(), StoreMyAccountPage.pageTitle);

        myAccountPage.logout();
        Assert.assertEquals(driver.getTitle(), StoreLoginPage.pageTitle);
    }

    //S1T2
    @Parameters("email")
    @Test(description = "Given I'm on login page, when I enter invalid credentials, then I should stay on login page and " +
            "get a failed login message.")
    public void loginWithInvalidCredentialsShouldFail(String email) {
        StoreLoginPage loginPage = new StoreLoginPage(driver)
                .enterEmail(email)
                .enterPassword("wrongpassw0rd88")
                .clickSignInBtnExpectingFailure();

        Assert.assertEquals(driver.getTitle(), StoreLoginPage.pageTitle);
        Assert.assertEquals(loginPage.getFailedLoginMessage(), failedLoginMessage);
    }

    //S1T3
    @Parameters("password")
    @Test(description = "Given I'm on login page, when I type the password, then it should be hidden. " +
            "Given I'm on login page, when the password is typed and I click on show button, then I should see my password.")
    public void passwordIsHiddenAfterTypedAndShownAfterShowButtonIsClicked(String password) {
        StoreLoginPage loginPage = new StoreLoginPage(driver);
        loginPage.enterPassword(password);

        Assert.assertEquals(loginPage.isPasswordHidden(), true);
        loginPage.showOrHidePassword();
        Assert.assertEquals(loginPage.isPasswordHidden(), false);
    }
}
