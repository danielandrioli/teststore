package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.store.StoreLoginPage;
import com.daniboy.pageobjects.store.StoreMyAccountPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.daniboy.util.Constants.failedLoginMessage;
import static com.daniboy.util.Constants.storeTestSiteBaseURL;

public class LoginTest extends BaseWebTest {

    @Test(priority = -1)
    public void enterUrl() {
        driver.get(storeTestSiteBaseURL + "login?back=my-account");
    }

    @Parameters({"email", "password"})
    @Test
    public void loginWithValidCredentialsThenLogout(String email, String password) {
        StoreMyAccountPage myAccountPage = new StoreLoginPage(driver)
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInBtn();
        Assert.assertEquals(driver.getTitle(), StoreMyAccountPage.pageTitle);

        myAccountPage.logout();
        Assert.assertEquals(driver.getTitle(), StoreLoginPage.pageTitle);
    }

    @Parameters("password")
    @Test
    public void passwordIsShownAfterShowBtnIsClicked(String password) {
        StoreLoginPage loginPage = new StoreLoginPage(driver);
        loginPage.enterPassword(password);

        Assert.assertEquals(loginPage.isPasswordHidden(), true);
        loginPage.showOrHidePassword();
        Assert.assertEquals(loginPage.isPasswordHidden(), false);
        loginPage.clearFields();
    }

    @Parameters("email")
    @Test
    public void failToLoginWithInvalidPassword(String email) {
        StoreLoginPage loginPage = new StoreLoginPage(driver)
                .enterEmail(email)
                .enterPassword("wrongpassw0rd88")
                .clickSignInBtnExpectingFailure();

        Assert.assertEquals(driver.getTitle(), StoreLoginPage.pageTitle);
        Assert.assertEquals(loginPage.getFailedLoginMessage(), failedLoginMessage);
        loginPage.clearFields();
    }
}
