package main.java.com.rp.automation.appln.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import main.java.com.rp.automation.base.BaseTest;
import main.java.com.rp.automation.customisedexception.FrameworkException;

import com.relevantcodes.extentreports.LogStatus;

public class Gmail_Pwd_Page extends BaseTest{

	@FindBy(name="password")
	private static WebElement password;
	
	@FindBy(xpath="//*[@name='passwordNext']")
	private static WebElement signIn;
	
	@FindBy(xpath="//span[contains(text(),'Forgot password?')]")
	private static WebElement frgtPwd;
	
	@FindBy(xpath="//a[contains(text(),'Learn more')]")
	private static WebElement learnMore;
	
	@FindBy(xpath="//span[contains(text(),'Create account')]")
	private static WebElement createAcc;
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
	
	/*
	 * public static void enterPassword(String pwdTestData) throws
	 * FrameworkException { if (password.isDisplayed()&&password.isEnabled()) {
	 * getExtentTest().log(LogStatus.PASS, "PASSWORD element is enabled");
	 * password.clear(); getExtentTest().log(LogStatus.PASS,
	 * "Clearing PASSWORD text field"); password.sendKeys(pwdTestData);
	 * getExtentTest().log(LogStatus.PASS,
	 * "Data Typing Action is done on PASSWORD");
	 * 
	 * } else { getExtentTest().log(LogStatus.FAIL,
	 * "PASSWORD element is not enabled"); FrameworkException exception = new
	 * FrameworkException("PASSWORD element is not enabled"); throw exception; } }
	 * 
	 * public static void clickNxtBtn() { SoftAssert assert1 = new SoftAssert();
	 * getExtentTest().log(LogStatus.PASS, "Next button is enabled");
	 * assert1.assertTrue(signIn.isDisplayed()&&signIn.isEnabled()); signIn.click();
	 * getExtentTest().log(LogStatus.PASS, "Next button is clicked");
	 * 
	 * }
	 * 
	 * public static void clickOnCreateAcc() {
	 * Assert.assertTrue(frgtPwd.isDisplayed()&&frgtPwd.isEnabled());
	 * frgtPwd.click(); }
	 */
	
	static
	{
		PageFactory.initElements(getDriver(), Gmail_Pwd_Page.class);
	}
	
}
