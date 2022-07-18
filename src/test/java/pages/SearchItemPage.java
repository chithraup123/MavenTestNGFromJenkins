package pages;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.BaseClass;

public class SearchItemPage {

	@FindBy(xpath = "//div[@class=\"inventory_item_price\"]")
	List<WebElement> priceElements;

	@FindBy(xpath = "//a[@class =\"shopping_cart_link\"]/span")
	WebElement cartText;

	WebDriver driver = BaseClass.driver;

	WebDriverWait wait;

	public SearchItemPage() {
		PageFactory.initElements(driver, this);
	}

	public String addHighestPricedItemToCart() {

		String btnText = null;
		double maxPrice = priceElements.stream()
				.mapToDouble(e -> Double.parseDouble(e.getText().trim().replace("$", ""))).max().getAsDouble();

		// div[normalize-space()='$49.99']//following-sibling::button[text()="Add to
		// cart"]
		// The above xpath contains normalize-space function to remove whitespace from
		// the element text values
		String xpath = "//div[normalize-space()='$" + maxPrice + "']//following-sibling::button[text()='Add to cart']";
		WebElement btnAddToCart = driver.findElement(By.xpath(xpath));
		btnAddToCart.click();

		if (cartText.isDisplayed()) {
			String numItems = cartText.getText();
			System.out.println(
					numItems + " items has been added to the Cart..............................................");
		}
		try {
			btnText = btnAddToCart.getText();
		} catch (StaleElementReferenceException stEx) {
			System.out.println(
					"Stale Element Exception Handled**************************************************************");
			btnText = driver.findElement(By.xpath("//div[normalize-space()='$" + maxPrice + "']//following-sibling::button")).getText();
		}
		return btnText;

	}
	
	// the script is prepared in browser-->sources-->snippets-->add new snippet with example.js and write the code and try to execute from there
	// If its working from there, then we can copy the script here, it is faster than the above method
	public void addToCartUsingJS() {
		
		String script = "var max = 0\r\n"
				+ "document.getElementsByClassName('inventory_item_price').forEach(e=>{\r\n"
				+ "    newVal = parseFloat(e.innerText.split('$')[1]);\r\n"
				+ "    if (max < newVal) {\r\n"
				+ "        max = newVal;        \r\n"
				+ "    }\r\n"
				+ "})\r\n"
				+ "\r\n"
				+ "console.log(max)\r\n"
				+ "\r\n"
				+ "var xpath=\"//div[normalize-space()='$\" + max + \"']//following-sibling::button[text()='Add to cart']\";\r\n"
				+ "var elemnt = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\r\n"
				+ "elemnt.click();";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}
	
}
