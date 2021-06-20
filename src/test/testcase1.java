package test;

import org.testng.annotations.Test;

public class testcase1 {

	@Test(priority = 0, description = "Verified Home Page", groups= {"Sanity"})
	public void HomeTest() {
		
		System.out.println("Inside Home Test");
	}

	@Test(priority = 2)
	public void HomeTest1() {
		
		System.out.println("Inside Home Test1");
	}
	//@Test(dependsOnMethods = "HomeTest")
	@Test(priority = 1, dependsOnMethods = "HomeTest")
	public void HomeTest2() {
		
		System.out.println("Inside Home Test2");
	}
}
