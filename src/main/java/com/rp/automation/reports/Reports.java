package main.java.com.rp.automation.reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
public static void main(String[] args) {
	
	ExtentReports extentReports = new ExtentReports("D:\\8AMBATCH\\frameworks\\Reports\\report.html");
	ExtentTest extentTest = extentReports.startTest("hello");
	extentTest.log(LogStatus.PASS, "Browser is opened");
	extentTest.log(LogStatus.PASS, "url is entered");
	extentTest.log(LogStatus.PASS, "DTA is done on USERNAME");
	extentTest.log(LogStatus.PASS, "Clicked on next button");
	
	extentReports.flush();
	extentReports.endTest(extentTest);
	extentReports.close();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
