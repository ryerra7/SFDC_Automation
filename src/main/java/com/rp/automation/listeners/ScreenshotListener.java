package main.java.com.rp.automation.listeners;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import main.java.com.rp.automation.utilities.ScreenshotUtility;

public class ScreenshotListener implements ITestListener{

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Test Suite is finished");
	}
	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("Test Suite is started");
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}
	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("TC IS FAILED: " +arg0.getName());
		try {
			ScreenshotUtility.takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("TC IS SKIPPED: " +arg0.getName());
		try {
			ScreenshotUtility.takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("TC IS STARTED: " +arg0.getName());
	}
	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("TC IS SUCCESS: " +arg0.getName());
	}
}
