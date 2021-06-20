package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testcase2 {
		
		@BeforeGroups("Sanity")
		public void BfrGpr() {
			System.out.println("Before Group");
		}
	
		@BeforeTest
		public void BfrTst() {
			System.out.println("Before Test");
		}
		@BeforeMethod
		public void Setup() {
			System.out.println("Before Method");
		}

		@Test(groups = {"Sanity"})
		public void LoginTest(){
			
			System.out.println("Inside LoginTest");
		}
		
		@Test(groups = {"Regression"})
		public void SignUp() {
			System.out.println("Inside SignUp");
		}
		
		@AfterMethod
		public void AfterMeth() {
			System.out.println("After Method");
		}
		
		@AfterTest
		public void AftrTst() {
			System.out.println("After Test");
		}
	}


