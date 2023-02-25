package Utils;

import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testClasses.BaseTest;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Case Execution Started: " + result.getName() + "|" + result.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Case Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Case Failed: " + result.getName());
    }
}
