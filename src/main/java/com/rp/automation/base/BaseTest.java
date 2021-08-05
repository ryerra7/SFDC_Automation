package main.java.com.rp.automation.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import main.java.com.rp.automation.customisedexception.FrameworkException;
import main.java.com.rp.automation.utilities.ScreenshotUtility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.After;
import io.cucumber.java.Before;


public class BaseTest {

	protected static WebDriver driver;
	private static String currentDirectory;
	private static String tcName;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	@Parameters({"browserName"})
	@BeforeSuite
	public void openBrowser(@Optional("chrome")String browserName) throws InterruptedException {
		currentDirectory = System.getProperty("user.dir");
		if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", currentDirectory+"\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			init();
		}
		else if (browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", currentDirectory+"\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			init();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", currentDirectory+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			init();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", currentDirectory+"\\Drivers\\geckodriver.exe");
			driver = new EdgeDriver();
			init();
		}
	}
	
	private void init() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
	}
	
	@AfterSuite
	public void closeBrowser() throws main.java.com.rp.automation.customisedexception.FrameworkException {
		if (driver!=null) {
			driver.close();
		}
		else {
			//throw exception
			System.out.println("DRIVER IS POINTING TO NULL");
			FrameworkException exception = new main.java.com.rp.automation.customisedexception.FrameworkException("DRIVER IS POINTING TO NULL");
			throw exception;
		}
	}

	/*
	 * @BeforeMethod public void beforeTCExecution(Method method) {
	 * System.out.println("Hooks Before"); String tcName = method.getName();
	 * System.out.println("Current execution TCName is: " +tcName); extentTest =
	 * extentReports.startTest(tcName); System.out.println("ET: " +extentTest); }
	 * //@AfterMethod
	 * 
	 * @AfterMethod public void afterTCExecution(ITestResult result) throws
	 * IOException { System.out.println("Hooks After"); String tcName =
	 * result.getName(); if (result.getStatus() == ITestResult.SUCCESS) {
	 * extentTest.log(LogStatus.PASS, "TC IS PASSED & TC NAME IS: " +tcName);
	 * System.out.println("TC IS PASSED & TC NAME IS: " +tcName); } else if
	 * (result.getStatus() == ITestResult.FAILURE) { extentTest.log(LogStatus.FAIL,
	 * "TC IS FAILED & TC NAME IS: " +tcName); //extentTest.log(LogStatus.FAIL,
	 * result.getStatus().); System.out.println("TC IS FAILED & TC NAME IS: "
	 * +tcName); String imagePath = ScreenshotUtility.takeScreenshot();
	 * //getExtentTest().addScreenCapture(imagePath);
	 * System.out.println("TC IS FAILED SO TAKING SCREENSHOT"); } else if
	 * (result.getStatus() == ITestResult.SKIP) { extentTest.log(LogStatus.SKIP,
	 * "TC IS SKIPPED & TC NAME IS: " +tcName); //extentTest.log(LogStatus.FAIL,
	 * result.getThrowable()); System.out.println("TC IS SKIPPED & TC NAME IS: "
	 * +tcName); String imagePath = ScreenshotUtility.takeScreenshot();
	 * //getExtentTest().addScreenCapture(imagePath);
	 * System.out.println("TC IS SKIPPED SO TAKING SCREENSHOT"); }
	 * extentReports.endTest(extentTest); }
	 */

	@BeforeTest
	public void initReports() {
		System.out.println("Before Test");
		extentReports = new ExtentReports(currentDirectory+"\\Reports\\report.html");
	}
	@AfterTest
	public void generateReports() {
		System.out.println("After Test");
		extentReports.flush();
		extentReports.close();
	}
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static String getCurrentDirectory() {
		return currentDirectory;
	}
	
	public static String getTcName() {
		return tcName;
	}
	
	public static ExtentTest getExtentTest() { 
		return extentTest; 
	}
}