package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	
	XSSFWorkbook wbook;
	XSSFSheet sheet;
	SoftAssert soft = new SoftAssert();
	
	@BeforeMethod
	public void setup() throws IOException {
		
		driver = new ChromeDriver();
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		driver.get("https://www.simplilearn.com/");
		
		report = new ExtentReports("LoginTest.html");
		
		FileInputStream fis = new FileInputStream("ExcelData.xlsx");
		
		wbook = new XSSFWorkbook(fis);
		
		sheet = wbook.getSheetAt(0);
		
	}
	
	@AfterTest
	public void teardown() {
		
		report.endTest(test);
		
		report.flush();
		
		driver.quit();
		//soft.assertAll();
	}
}
