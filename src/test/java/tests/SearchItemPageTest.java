package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.SearchItemPage;

public class SearchItemPageTest extends BaseClass {

	LoginPage loginPage;
	SearchItemPage searchItemPage;

	public final static String USER = "standard_user";
	public final static String PWD = "secret_sauce";

	@BeforeMethod
	public void initialize() {
		loginPage = new LoginPage();
		searchItemPage = new SearchItemPage();
		loginPage.login(USER, PWD);
	}
	
	@Test
	public void addHighPricedItemTest() {
//		String btnText = searchItemPage.addHighestPricedItemToCart();
//		Assert.assertEquals("REMOVE", btnText);
		searchItemPage.addToCartUsingJS();
	}	
}
