package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;

public class BaseClass {

	public static WebDriver driver;

	XSSFWorkbook wbook;
	XSSFSheet valid_data_sheet;
	XSSFSheet invalid_data_sheet;

	@BeforeTest
	public void dataSetup() throws IOException {
		FileInputStream input = new FileInputStream("dataExcel.xlsx");
		wbook = new XSSFWorkbook(input);
		valid_data_sheet = wbook.getSheet("valid-data");
		invalid_data_sheet = wbook.getSheet("invalid-data");
	}

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	public void dataTearDown() throws IOException {
		wbook.close();
	}
}
