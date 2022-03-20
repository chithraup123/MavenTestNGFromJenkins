package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginPageTest extends BaseClass {

	String userName;
	String pwd;

	LoginPage loginPage;

	@BeforeMethod
	public void initialize() {
		loginPage = new LoginPage();
	}

	@Test
	public void ValidLoginTest() {
		userName = valid_data_sheet.getRow(1).getCell(0).getStringCellValue();
		pwd = valid_data_sheet.getRow(1).getCell(1).getStringCellValue();
		loginPage.login(userName, pwd);
		String expUrl = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(loginPage.getCurrentUrl(), expUrl, "Expected URL and Actual URL are NOT EQUAL");
	}

	@Test
	public void InvalidLoginTest() {
		userName = invalid_data_sheet.getRow(1).getCell(0).getStringCellValue();
		pwd = invalid_data_sheet.getRow(1).getCell(1).getStringCellValue();
		loginPage.login(userName, pwd);
		String expMsg = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(loginPage.getErrorMsg(), expMsg, "Expected and Actual are NOT EQUAL");
	}
}
