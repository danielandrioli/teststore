package com.daniboy;

import static com.daniboy.util.TestUtils.takeScreenshot;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ITestListenerImpl implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Teste falhou. Nome do método: " + result.getName());
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseWebTest) testClass).getDriver();
        try {
            takeScreenshot(driver);
        } catch (IOException e) {
            System.out.println("Deu merda na hora de tirar ss");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getName() + " test skipped!");
    }
}
