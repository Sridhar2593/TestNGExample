package Shopping;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SwagLabs {

	    WebDriver driver;
		ExtentReports report;
		ExtentTest test;
		SoftAssert soft = new SoftAssert();
		
		@BeforeTest
		public void setup() {
			
			driver = new ChromeDriver();
			
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			
			driver.get("https://www.saucedemo.com/");
			
			report = new ExtentReports("Shopping.html");
			
		}
		
		@Parameters({"username","password"})
		@Test
		public void LoginTest(String uname, String pass) {
			
			test = report.startTest("Login Test Case");
			WebElement UserName = driver.findElement(By.xpath("//input[@id='user-name']"));
			
			UserName.sendKeys(uname);
			
			WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
			
			Password.sendKeys(pass);
			
			WebElement Login = driver.findElement(By.xpath("//input[@id='login-button']"));
			
			Login.click();
		}
		
		@Test(dependsOnMethods="LoginTest")
		public void AddtoCart() {
			
			WebElement addCart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
			WebDriverWait wait = new WebDriverWait(driver,30); //--- Explicit Wait
			
			wait.until(ExpectedConditions.elementToBeClickable(addCart));
			addCart.click();
			WebElement ShoppingCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
			ShoppingCart.click();
			WebElement CartCheck = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
			String ItemCartCheck = CartCheck.getText();
			
			String ExpectedItem = "Sauce Labs Backpack";
			Assert.assertTrue(CartCheck.isDisplayed());
			Assert.assertEquals(ItemCartCheck, ExpectedItem);
		}
		
		@AfterTest
		public void teardown() {
			
			driver.quit();
			
		}
}
