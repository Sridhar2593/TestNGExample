package test;

import org.testng.annotations.Test;

import Shopping.SwagLabs;
import pages.LoginPage;

public class ShoppingTest extends ShoppingBaseClass {

	@Test
	public void LoginTest2() {
		
		SwagLabs lnobj = new SwagLabs();
		String uname = sheet.getRow(1).getCell(0).getStringCellValue();
		String pass = sheet.getRow(1).getCell(1).getStringCellValue();
		
		lnobj.LoginTest(uname, pass);
	}
	
	@Test(dependsOnMethods="LoginTest2")
	public void addcartTest() {
		
		SwagLabs addct = new SwagLabs();
		addct.AddtoCart();
		
	}
}
