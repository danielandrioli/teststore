package tests;

import com.daniboy.BaseWebTest;
import com.daniboy.pageobjects.AutomationHomePage;
import com.daniboy.pageobjects.store.StoreHomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.daniboy.util.Constants.automationTestSiteBaseURL;

public class AutomationSiteNavigationTest extends BaseWebTest {

    @Test(priority = -1)
    public void enterUrl() {
        driver.get(automationTestSiteBaseURL);
    }

    @Test
    public void enterTestStore() {
        AutomationHomePage home = new AutomationHomePage(getDriver());
        home.clickOnTestStore();
        Assert.assertEquals(driver.getTitle(), StoreHomePage.pageTitle);
    }

    @AfterMethod
    public void goBackToHomePage(){
        if (!driver.getTitle().equals(AutomationHomePage.pageTitle)) {
            driver.navigate().back();
        }
    }
}
