package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPage;

public class LoginPageTest extends BaseClass {

	String userName;
	String pwd;

	LoginPage loginPage;

	@BeforeMethod
	public void initialize(Method method) {
		loginPage = new LoginPage();
		System.out.println("Inside before method of LoginPageTest");
		// extReport.startTest(method.getName());
	}

	@Test
	public void ValidLoginTest() {

		System.out.println("Inside ValidLoginTest");
		extTest.log(LogStatus.PASS, "Reading valid username from excel sheet");

		userName = valid_data_sheet.getRow(1).getCell(0).getStringCellValue();
		extTest.log(LogStatus.PASS, userName);
		extTest.log(LogStatus.PASS, "Reading valid password from excel sheet");
		pwd = valid_data_sheet.getRow(1).getCell(1).getStringCellValue();
		extTest.log(LogStatus.PASS, pwd);
		extTest.log(LogStatus.PASS, "Valid Login to the application");
		loginPage.login(userName, pwd);
		String expUrl = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(loginPage.getCurrentUrl(), expUrl, "Expected URL and Actual URL are NOT EQUAL");
	}

	@Test
	public void InvalidLoginTest() {
		extTest.log(LogStatus.PASS, "Reading invalid username from excel sheet");
		userName = invalid_data_sheet.getRow(1).getCell(0).getStringCellValue();
		extTest.log(LogStatus.PASS, userName);
		extTest.log(LogStatus.PASS, "Reading invalid password from excel sheet");
		pwd = invalid_data_sheet.getRow(1).getCell(1).getStringCellValue();
		extTest.log(LogStatus.PASS, pwd);
		extTest.log(LogStatus.PASS, "Valid Login to the application");
		loginPage.login(userName, pwd);
		String expMsg = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(loginPage.getErrorMsg(), expMsg, "Expected and Actual are NOT EQUAL");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Inside after method of LoginPageTest");
		// extReport.endTest(extTest);
	}
}
