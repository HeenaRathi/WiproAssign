package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Helper.LoggerHelper;
import Helper.ScrollUtil;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends base {

	@FindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	public WebElement searchBar;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	
	@FindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	public WebElement searchText;
	
	@FindBy(id="com.amazon.mShop.android.shopping:id/item_title")
	public WebElement selectList;

	public HomePage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	public void clickSearchTab() {
		boolean a = searchBar.isDisplayed();
		if (a) {
			log.info("Click on searchBar ");
			searchBar.click();
		} else {
			log.error("Unable to click on SearchBar");
		}
	}

	@SuppressWarnings("deprecation")
	public void searchText(String text) {
		
		searchBar.sendKeys(text);
		driver.getKeyboard().pressKey(Keys.ENTER);

	}

	public void scrollList(AndroidDriver<AndroidElement> driver, String tex) {
		ScrollUtil sc= new ScrollUtil(driver);
		sc.scrollToText(tex);
		selectList.click();
		log.info("scroll the list");
		
	}
}
