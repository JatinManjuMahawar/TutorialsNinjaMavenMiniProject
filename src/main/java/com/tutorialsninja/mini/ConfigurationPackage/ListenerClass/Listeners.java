package com.tutorialsninja.mini.ConfigurationPackage.ListenerClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.mini.BasePackage.Utilities;
import com.tutorialsninja.mini.ConfigurationPackage.ExtentReporting.ExtentReportingClass;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener
{

    ExtentReports extentReports;
    ExtentTest extentTest;
    ExtentReportingClass erc = new ExtentReportingClass();
    String testName;
    String userDirPath = System.getProperty("user.dir").replace("\\", "/");
    @Override
    public void onStart(ITestContext context) {
        extentReports = erc.generateEctentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO, testName + " Started Executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, testName + " Executed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, testName + " Failed");
        String screenshotPath;
        WebDriver driver = null;
        try{
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        }
        catch(Throwable ex){
            ex.printStackTrace();
        }
        try {
            screenshotPath = Utilities.captureRobotScreenshot(driver, testName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        extentTest.addScreenCaptureFromPath(screenshotPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName +  " got failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName + " Got Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        File file = new File(System.getProperty("user.dir")+"\\TestOutputs\\ExtentReporting\\ExtentReport.html");
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
