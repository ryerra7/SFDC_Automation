package main.java.com.rp.automation.base;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/*import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
*/import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import main.java.com.rp.automation.customisedexception.FrameworkException;


public class ActionTest extends BaseTest{
	public static void click(WebElement element) throws FrameworkException {
		if (element.isDisplayed()&&element.isEnabled()) {
			element.click();
		}
		else {
			FrameworkException exception = new FrameworkException("Element is not displayed");
			throw exception;
		}
	}
	public static void DTA(WebElement element,String testData) throws FrameworkException {
		Assert.assertTrue(element.isDisplayed()&&element.isEnabled());
		element.clear();
		element.sendKeys(testData);
		//System.out.println("55555555555555555555"+getExtentTest());
	   getExtentTest().log(LogStatus.PASS, "DTA is done successfully: "+testData);
		
	}
	
	public static void actionsClick(WebElement element) {
		Actions actions = new Actions(getDriver());
		actions.click(element).build().perform();
	}
	
	public static void dropDowns(WebElement element,String how,String value)
	{
		Assert.assertTrue(element.isDisplayed()&&element.isEnabled());
		Select select = new Select(element);
		if (how.equalsIgnoreCase("text")) {
			select.selectByVisibleText(value);
		}
		else if (how.equalsIgnoreCase("value")) {
			select.selectByValue(value);
		}
		else{
			int index = Integer.parseInt(value);
			select.selectByIndex(index);
		}
	}
	
	public static String handleMultiWindows() {
		String curWindow = getDriver().getWindowHandle();
		Set<String> windows = getDriver().getWindowHandles();
		
		for(String win : windows)
		{
			if (!curWindow.equalsIgnoreCase(win)) {
				getDriver().switchTo().window(win);
				curWindow = getDriver().getWindowHandle();
			}
		}
		return curWindow;
	}
	
	public static String handleMultiWindows(int windowIndex) {
		String curWindow = getDriver().getWindowHandle();
		Set<String> windows = getDriver().getWindowHandles();
		List<String> windows1 = new ArrayList<String>(windows);
		String window = windows1.get(windowIndex);
		getDriver().switchTo().window(window);
		curWindow = getDriver().getWindowHandle();
		return curWindow;
	}
	public static void clickByJs(WebElement element) {
        try {
     	   JavascriptExecutor executor = (JavascriptExecutor)driver;
     	   executor.executeScript("arguments[0].click();", element);
     	   } catch (Exception e) {
            throw new FrameworkException("Unknown exception occured while clicking the element"
                    + e.getClass() + "---" + e.getMessage());
            }
      }
	public static void highlightElement(WebElement element) {
		 JavascriptExecutor js = (JavascriptExecutor)driver;
    	  
		js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", element);
		js.executeScript("arguments[0].setAttribute('style','border: solid 4px white');", element);
		js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", element);
		js.executeScript("arguments[0].setAttribute('style','border: solid 4px white');", element);
		js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", element);
		js.executeScript("arguments[0].setAttribute('style','border: solid 4px white');", element);
		js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;');", element);
		js.executeScript("arguments[0].setAttribute('style','border: solid 4px white');", element);
		js.executeScript("arguments[0].setAttribute('style','border: solid 0px white');", element);
	}

	public static void clickThruActions(WebElement element, String desc) {
		try {
			if (element.isDisplayed()) {
				highlightElement(element);
				Actions ac = new Actions(driver);
				ac.moveToElement(element);
				ac.doubleClick();
			} else {
				throw new FrameworkException("Field: " + desc + " is not displayed on UI.");
			}
		} catch (Exception ee) {
			throw new FrameworkException(
					"Field: " + desc + " is not displayed on UI." + "---" + ee.getClass() + "---" + ee.getMessage());
		}
	}
	
	public static String generatingRandomCharacters(int n) {
        try{
        String primaryCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + " " + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {

            int index = (int) (primaryCharacters.length() * Math.random());

            sb.append(primaryCharacters.charAt(index));
        }
        return sb.toString();
        }
                catch (FrameworkException e) {
            throw new FrameworkException(e.getMessage());
        } catch (Exception e) {
            throw new FrameworkException("Unknown exception occured while verifying attribute"
                    + e.getClass() + "---" + e.getMessage());
        }
}
	
	public static String getAttribute(WebElement element, String attribute, String desc) {
		// isDisplayed(element,desc);
		try {
			String value;
			if (attribute.equals("text")) {
				if (element.getText() != null) {
					value = element.getText();
				} else {
					value = "";
				}
			} else {
				if (element.getAttribute(attribute) != null) {
					value = element.getAttribute(attribute);
				} else {
					value = "";
				}
			}
			return value;
		} 
			 catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while retrieving Attribute: "
					+ attribute.toUpperCase() + " value for " + desc + "---" + e.getClass() + "---" + e.getMessage());
		}
	}

	
	public static int getListSize(List<WebElement> list, String listName) {
		try {
			return list.size();
		} catch (Exception e) {
			throw new FrameworkException("Unknown exception encountered while retrieving size of List: " + listName);
		}
	}

	public static String getCurrentURL() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			throw new FrameworkException(
					"Unknow exception encountered while gettting current url." + e.getClass() + "---" + e.getMessage());
		}
	}
	
	public static String getcurrentwindowTitle() {
		String title = null;
		title = driver.getTitle();
		if (title != null) {
			return title;
		} else {
			throw new FrameworkException("Window " + title + " not found.");
		}

	}
	public static int getFolderfileCount(String FileDir) {
		int noOfFilesinFolder = 0;
		try {
			File directory = new File(FileDir);
			noOfFilesinFolder = directory.list().length;
			System.out.println("File Count:" + noOfFilesinFolder);

		} catch (NoSuchElementException e) {
			throw new FrameworkException("Exception in getFolderfileCount as " + e.getMessage());
		}
		return noOfFilesinFolder;
	}
	public static String getListItems(List<WebElement> listItems, String attribute, String listName) {
		String items = "";
		try {
			if (listItems.size() > 0) {
				String item;
				for (int i = 0; i < listItems.size(); i++) {
					item = "";
					if (attribute.toLowerCase().equals("text")) {
						item = listItems.get(i).getText();
					} else {
						item = listItems.get(i).getAttribute(attribute);
					}
					if (items.equals("")) {
						items = item;
					} else {
						items += ":" + item;
					}
				}
				return items;
			} else {
				
					throw new FrameworkException("List: " + listName + " not found.");
				
			}

		} catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while getting list value from: " + listName + "---"
					+ e.getClass() + "---" + e.getMessage());
		}
	}
	
	public static String getWebPageText() {
		try {
			/// Go to top first
			WebElement body = driver.findElement(By.tagName("body"));
			String bodyText = body.getText();
			return bodyText;
		} catch (Exception e) {
			throw new FrameworkException(
					"Unknown exception occured while typing on: " + e.getClass() + "---" + e.getMessage());
		}
	}
	public static void waitTill(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {

		}
	}

	public static void verifyElement(WebElement element,String desc) {
		if (element.isDisplayed()&&element.isEnabled()) {
			getExtentTest().log(LogStatus.PASS, desc);
		}
		else {
			getExtentTest().log(LogStatus.FAIL, desc);
		}
	}
	
	public static void verifyElements(List<WebElement> element,String desc) {
		try {
		WebDriverWait wait = new WebDriverWait(getDriver(), 89);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
		getExtentTest().log(LogStatus.PASS, desc);
		}
		catch(Exception e) {
			getExtentTest().log(LogStatus.FAIL, desc);
		}
	}
	
	
	public static void scrollUpPageTopJS(String desc) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
			waitTill(2);
		} catch (Exception e) {
			throw new FrameworkException(
					"Unknown exception occured while typing on: " + desc + e.getClass() + "---" + e.getMessage());
		}
	}
	
	public static void scroll(WebElement element) {
		try {
			element.isDisplayed();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)", "");
			waitTill(2);
			} catch (NoSuchElementException e) {
							throw new FrameworkException(element.toString() + " not found while scrolling to element.");
			

		} catch (Exception e) {
			if (e instanceof FrameworkException) {
				throw e;
			} else {
				throw new FrameworkException("Unknown exception encountered while scrolling to " + element.toString()
						+ "---" + e.getClass() + "---" + e.getMessage());
			}

		}
	
	}
	
	public static void waitForPageToLoad() {
		try {
			String pageLoadStatus;
			do {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				pageLoadStatus = (String) js.executeScript("return document.readyState");
				Thread.sleep(100);
				
				System.out.print(".");
			} while (!pageLoadStatus.equals("complete"));
			System.out.println("Page Loaded.");
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static void waitTill(WebElement element, String state) {
		try {
			// if (TestSetup.isMobile) {
			// scroll(element);
			// }
			
			WebDriverWait	wait = new WebDriverWait(driver, 30);
			
			switch (state.toLowerCase()) {
			case "visible":
				wait.until(ExpectedConditions.visibilityOf(element));
				break;
			case "enable":
				wait.until(ExpectedConditions.elementToBeClickable(element));
				break;
			case "invisible":
				element.isDisplayed();
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
				break;
			default:
				wait.until(ExpectedConditions.visibilityOf(element));
			}
			} catch (StaleElementReferenceException e) {
				throw new FrameworkException(
						"Page refreshed while waiting for element : *  '" + element.toString() + "'");
			
		} catch (TimeoutException e) {
			throw new FrameworkException(
					"Element : *  '" + element.toString() + "' not found within defined time limit.");
		} catch (NoSuchElementException e) {
			throw new FrameworkException("Element : *  '" + element.toString() + "' not found in DOM.");
		} catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while waiting for element: *  '"
					+ element.toString() + "'---" + e.getClass() + "---" + e.getMessage());
		}

	}


	public static String verifyLinkActive(String linkUrl) {
		// Report.infoTest(">>>>>>>>>> In Function >>> "+ new
		// Object(){}.getClass().getEnclosingMethod().getName());
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				// System.out.println(linkUrl+" -
				// "+httpURLConnect.getResponseMessage());

				return httpURLConnect.getResponseCode() + " " + httpURLConnect.getResponseMessage();
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				// System.out.println(linkUrl+" -
				// "+httpURLConnect.getResponseMessage() + " - "+
				// HttpURLConnection.HTTP_NOT_FOUND);
				return httpURLConnect.getResponseCode() + " " + httpURLConnect.getResponseMessage();
			} else {
				return httpURLConnect.getResponseCode() + " " + httpURLConnect.getResponseMessage();
			}
		} catch (Exception e) {
			System.out.println("Invalid link provided. Exception " + e.getMessage());
			return "Invalid link provided";
		}
	}

	public static void executeBatchFile(String filePath) {
		System.out.println("In Function >>>  executeBatchFile");
		String BatchFilePath = filePath; // Runtime
		Runtime.getRuntime();
		try {
			String[] command = { "cmd.exe", "/C", "Start", BatchFilePath };
			Process p = Runtime.getRuntime().exec(command);
			waitTill(3);
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(" Exception >> " + e.getMessage());
		}

	}

	public static boolean typeWithSplitChar(WebElement element, String Value, boolean need2Clean) {
		System.out.println(">>>>>>>>>> In Function >>> " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		boolean successflag = false;
		try {
			waitTill(element, "visible");
			if (element.isEnabled()) {
				if (need2Clean) {
					element.clear();
				}
				String[] Values = Value.split("(?!^)");
				for (int i = 0; i < Value.length(); i++) {
					element.sendKeys(Values[i]);
				}

				successflag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		waitForPageToLoad();
		return successflag;

	}
	public static void scrollBottomJS(String desc) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
			waitTill(2);
		} catch (Exception e) {
			throw new FrameworkException(
					"Unknown exception occured while typing on: " + desc + e.getClass() + "---" + e.getMessage());
		}
	}

	public static void switchToNewWindow(String title) {
		boolean windowFound = false;
		try {
			String parentWindow = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			Thread.sleep(10);
			if (handles.size() > 1) {
				for (String windowHandle : handles) {
					if (!windowHandle.equals(parentWindow)) {
						if (driver.switchTo().window(windowHandle).getTitle().contains(title)) {
							driver.switchTo().window(windowHandle);
							// driver.manage().window().maximize();
							windowFound = true;
							break;
						}
					}
				}
			} else {
				if (driver.getTitle().contains(title)) {
					windowFound = true;
				}
			}
		} catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while switching to " + title + "---" + e.getClass()
					+ "---" + e.getMessage());
		}
		if (!windowFound) {
			throw new FrameworkException("Window " + title + " not found.");
		}

	}
	
	public static void switchtoiframe(String name) {
		try {
			driver.switchTo().frame(name);
			} catch (NoSuchFrameException e) {
				throw new FrameworkException("Frame " + name + " not found.");
			
		} catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while switching to Frame: " + name + "---"
					+ e.getClass() + "---" + e.getMessage());
		}

	}
	public static void verifyElementState(WebElement element, String expectedState, String fieldDesc) {
		try {
			scroll(element);
			if (expectedState.equalsIgnoreCase("enable")) {
				if (element.isEnabled()) {
					} else {
					highlightElement(element);
					throw new FrameworkException("Field '" + fieldDesc
							+ "' is not present in desired state. Expected State is: " + expectedState);
				}
			} else {
				if (element.isEnabled()) {
					highlightElement(element);
					throw new FrameworkException("Field '" + fieldDesc
							+ "' is not present in desired state. Expected State is: " + expectedState);
				} else {
					System.out.println(
							"Field '" + fieldDesc + "' verified successfully and is " + expectedState + ".");
				}
			}
		} catch (FrameworkException e) {
			
				throw new FrameworkException(e.getMessage());
			
		} catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while verifying states for: " + fieldDesc + "---"
					+ e.getClass() + "---" + e.getMessage());
		}
	}
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public static void closeCurrentwindow() {
		driver.close();
	}
	public static void mouseHover(WebElement element, String desc) {
		try {
			if (element.isDisplayed()) {
				highlightElement(element);
				Actions a = new Actions(driver);
				a.moveToElement(element).perform();
				waitTill(1);
			} else {
				throw new FrameworkException("Field: " + desc + " is not displayed.");
			}
			} catch (NoSuchElementException e) {
				throw new FrameworkException("Field " + desc + " not found.");
			
		} catch (FrameworkException e) {
			throw new FrameworkException(e.getMessage());
		} catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while hovering to: " + desc + "---" + e.getClass()
					+ "---" + e.getMessage());
		}
	}
	
	public static void navigatetoUrl(String url) {
		try {
			if (url != "") {
				driver.get(url);
				getExtentTest().log(LogStatus.PASS, "URL is entered as: "+url);
				Thread.sleep(8000);
				waitForPageToLoad();
				
			} else {
				getExtentTest().log(LogStatus.PASS, "URL is not entered as: "+url);
				throw new FrameworkException("Please enter the URL in config.");
			}
			
		} catch (Exception e) {
			throw new FrameworkException(
					"Unable to navigate to URL--- " + url + "---" + e.getClass() + "---" + e.getMessage());
		}
	}
	
	public static void refreshPage() {
		try {
			driver.navigate().refresh();
			waitTill(5);
			waitForPageToLoad();
		} catch (Exception e) {
			throw new FrameworkException("Unknown exception occured while refreshing the page." + "---" + e.getClass()
					+ "---" + e.getMessage());
		}
	}
	
	public static void readpdf(String textonPDF) throws IOException {
		switchToNewWindow(textonPDF.substring(5));
		URL pdfurl = new URL(getCurrentURL());
		/*
		 * BufferedInputStream bis = new BufferedInputStream(pdfurl.openStream());
		 * PDDocument doc = PDDocument.load(bis); String text = new
		 * PDFTextStripper().getText(doc); if (text.contains(textonPDF)) {
		 * System.out.println("Heading of the pdf verified-" + textonPDF); } else {
		 * System.out.println( "Heading of the pdf cannot be verified-" + textonPDF); }
		 * doc.close(); bis.close();
		 */
	}
	public static void selectValuefromDropdown(WebElement dropdown, String selectBy, String value) {
		try {
			Select select = new Select(dropdown);
			if (selectBy.toLowerCase().contains("value")) {
				select.selectByVisibleText(value);
			} else if (selectBy.toLowerCase().contains("index")) {
				select.selectByIndex(Integer.valueOf(value));
			} else {
				throw new FrameworkException(selectBy + " not configured. Please contact Automation Team.");
			}
		} catch (Exception e) {
			if (e instanceof FrameworkException) {
				throw e;
			} else {
				throw new FrameworkException("Unknown exception while selecting value from dropdown---" + e.getClass()
						+ "---" + e.getMessage());
			}
		}

	}
	public static void click(WebElement element, String desc) {
		highlightElement(element);
		if (element.isEnabled()) {
			// element.click();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);	
			waitForPageToLoad();
			getExtentTest().log(LogStatus.PASS,desc);
	}
	}
	
	public static void selectValuefromDropdown(List<WebElement> dd_Options, String value, boolean isDynamic) {
		WebElement option;
		boolean found = false;
		String listItems = "";
		try {
			if (value != "") {
				waitTill(dd_Options.get(0), "enable");
				for (int i = 0; i < dd_Options.size(); i++) {
					option = dd_Options.get(i);
					listItems += getAttribute(option, "text", "List Item") + " : ";
					if (option.getText().toLowerCase().equalsIgnoreCase(value.toLowerCase())) {
						click(option, value);
						found = true;
						break;
					}
				}
			}
			if (!found && isDynamic) {
				waitTill(5);
				// refreshPage();
				selectValuefromDropdown(dd_Options, value, false);
			}
			if (!found && !isDynamic) {
				throw new FrameworkException("Value '" + value + "' not found. Values from UI are " + listItems);
			}
			} catch (IndexOutOfBoundsException e) {
			
				throw new FrameworkException(
						"Drop down options not loaded in specified time while looking for " + value);
			
		} catch (Exception e) {
			throw new FrameworkException(
					"Unknown exception while selecting: " + value + "---" + e.getClass() + "---" + e.getMessage());
		}
	}

	
	
	}
