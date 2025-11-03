package com.cura.utils;

import com.cura.base.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Test Suite Started: " + context.getName());
        System.out.println("========================================");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Test Suite Finished: " + context.getName());
        System.out.println("Total Tests Run: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("========================================");
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\n>>> Test Started: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✓ Test Passed: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("✗ Test Failed: " + result.getMethod().getMethodName());
        System.out.println("Reason: " + result.getThrowable().getMessage());
        
        // Capture screenshot on failure
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testClass;
            if (baseTest.getDriver() != null) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(
                    baseTest.getDriver(), 
                    result.getMethod().getMethodName()
                );
                System.out.println("Screenshot saved at: " + screenshotPath);
            }
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⊗ Test Skipped: " + result.getMethod().getMethodName());
    }
}