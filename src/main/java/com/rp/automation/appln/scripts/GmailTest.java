package main.java.com.rp.automation.appln.scripts;
/*
 * package main.java.com.rameshsoft.automation.appln.scripts;
 * 
 * import java.io.FileInputStream; import java.io.FileNotFoundException; import
 * java.io.IOException; import java.sql.Driver; import java.util.Properties;
 * 
 * import org.apache.poi.EncryptedDocumentException; import
 * org.apache.poi.openxml4j.exceptions.InvalidFormatException; import
 * org.apache.poi.ss.usermodel.Cell; import
 * org.apache.poi.ss.usermodel.CellType; import org.apache.poi.ss.usermodel.Row;
 * import org.apache.poi.ss.usermodel.Sheet; import
 * org.apache.poi.ss.usermodel.Workbook; import org.openqa.selenium.By; import
 * org.openqa.selenium.WebElement; import org.testng.annotations.Test;
 * 
 * import main.java.com.rameshsoft.automation.base.BaseTest; import
 * main.java.com.rameshsoft.automation.supporters.ExcelReader; import
 * main.java.com.rameshsoft.automation.utilities.POJOUtility; import
 * main.java.com.rameshsot.automation.customisedexception.FrameworkException;
 * import main.java.hooks.Hooks;
 * 
 * import com.relevantcodes.extentreports.LogStatus;
 * 
 * public class GmailTest extends BaseTest{
 * 
 * String value;
 * 
 * @Test public void login() throws EncryptedDocumentException, IOException,
 * FrameworkException,Exception { Hooks.getExtentTest().log(LogStatus.PASS,
 * "Browser is opened "); String url =
 * POJOUtility.getConf().getValue("gmail_url"); System.out.println("URL IS: "
 * +url);
 * 
 * value = POJOUtility.getExcelReader().getSingleCellData("sheetName", "data",
 * 0, 0); getDriver().get(url); Hooks.getExtentTest().log(LogStatus.PASS,
 * "URL IS entered as: " +url);
 * 
 * String unid = POJOUtility.getOr().getValue("un_id");
 * Hooks.getExtentTest().log(LogStatus.PASS, "USERNAME Element locator is: "
 * +unid); WebElement un = getDriver().findElement(By.id(unid)); un.clear();
 * Hooks.getExtentTest().log(LogStatus.PASS, "CLEARED THE USERNAME FIELD");
 * un.sendKeys(value); Hooks.getExtentTest().log(LogStatus.PASS,
 * "Data Typing Action is done on USERNAME FIELD with test data: " +value);
 * 
 * String nxtXapth = POJOUtility.getOr().getValue("next_xpath");
 * Hooks.getExtentTest().log(LogStatus.PASS, "NEXT Element locator is: "
 * +nxtXapth); getDriver().findElement(By.xpath(nxtXapth)).click();
 * Hooks.getExtentTest().log(LogStatus.PASS, "Clicked on next button"); }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * }
 */