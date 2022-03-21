package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports extReport;
	public static ExtentTest extTest;

	XSSFWorkbook wbook;
	XSSFSheet valid_data_sheet;
	XSSFSheet invalid_data_sheet;

	@BeforeTest
	public void dataSetup() throws IOException {
		System.out.println("Inside before test of BaseClass");
		FileInputStream input = new FileInputStream("dataExcel.xlsx");
		wbook = new XSSFWorkbook(input);
		valid_data_sheet = wbook.getSheet("valid-data");
		invalid_data_sheet = wbook.getSheet("invalid-data");

		extReport = new ExtentReports("extent-report.html");
	}

	@BeforeMethod
	public void setUp(Method method) {
		extTest = extReport.startTest(method.getName());
		System.out.println("Inside before method of BaseClass");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void teardown() {
		System.out.println("Inside after method of BaseClass");
		extReport.endTest(extTest);
		driver.quit();
	}

	@AfterTest
	public void dataTearDown() throws IOException {
		System.out.println("Inside after test of BaseClass");

		wbook.close();
		extReport.flush();
		extReport.close();
	}
}
