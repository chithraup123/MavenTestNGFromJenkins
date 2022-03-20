package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tests.BaseClass;

public class LoginPage {

	@FindBy(xpath = "//input[@id='user-name']")
	WebElement username;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//div[@class='error-message-container error']//h3")
	WebElement errorMsg;

	@FindBy(xpath = "//input[@id='login-button']")
	WebElement loginButton;

	WebDriver driver = BaseClass.driver;

	public LoginPage() {

		PageFactory.initElements(driver, this);
	}

	public void login(String userName, String pwd) {

		username.sendKeys(userName);
		password.sendKeys(pwd);
		loginButton.click();
	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}

	public String getCurrentUrl() {

		return driver.getCurrentUrl();
	}
}
