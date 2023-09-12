package testcases;

import com.daniboy.BaseWebTest;
import static com.daniboy.util.Constants.automationTestSiteBaseURL;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BaseWebTest {

    @Test
    public void login() {
        getDriver().get(automationTestSiteBaseURL);
        Assert.fail();
    }
}
