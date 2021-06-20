package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	SoftAssert soft = new SoftAssert();
	
	@BeforeMethod
	public void setup() {
		
		driver = new ChromeDriver();
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		driver.get("https://www.simplilearn.com/");
		
		report = new ExtentReports("LoginTest.html");
		
	}
	
	@Parameters({"username","password"})
	@Test
	public void LoginTest1(String uname, String pass) {
		
		test = report.startTest("Login Test Case");
		
		WebElement LoginLink = driver.findElement(By.linkText("Log in"));
		
		LoginLink.click();
		
		test.log(LogStatus.PASS, "Successfully clicked on the Login Button");
		
		WebElement UserName = driver.findElement(By.name("user_login"));
		
		WebDriverWait wait = new WebDriverWait(driver,30); //--- Explicit Wait
		
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		
		UserName.sendKeys(uname);
		
		test.log(LogStatus.PASS, "Successfully enter the UserName");
		
		WebElement Password = driver.findElement(By.id("password"));
		
		Password.sendKeys(pass);
		
		test.log(LogStatus.PASS, "Successfully enter the Password");
		
		WebElement RememberMe = driver.findElement(By.className("rememberMe"));
		
		RememberMe.click();
		
		WebElement Login = driver.findElement(By.name("btn_login"));
		
		Login.click();
		
		test.log(LogStatus.PASS, "Successfully clicked on the Login Link");
		
		WebElement Error = driver.findElement(By.id("msg_box"));
		
		String ActError = Error.getText();
		
		String ExpectedError = "The email or password you have entered is invalid";
		
		//test.log(LogStatus.FAIL, "Expected and Actual value does not match");
		
		Assert.assertTrue(Error.isDisplayed());
		//soft.assertEquals(ActError, ExpectedError);
		try {
			
			Assert.assertEquals(ActError, ExpectedError);
			
		}catch(Throwable e) {
			
			test.log(LogStatus.FAIL, "Expected and Actual value does not match");
		}
		
		
	}
	
	@AfterTest
	public void teardown() {
		
		report.endTest(test);
		
		report.flush();
		
		driver.quit();
		//soft.assertAll();
	}
	

}
