package com.daniboy;

import static com.daniboy.util.TestUtils.takeScreenshot;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class ITestListenerImpl implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("Test failed. Method name: " + result.getName());
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseWebTest) testClass).getDriver();
        try {
            takeScreenshot(driver);
        } catch (IOException e) {
            System.out.println("Not able to take screenshot: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test skipped. Method name: " + result.getName());
    }
}
