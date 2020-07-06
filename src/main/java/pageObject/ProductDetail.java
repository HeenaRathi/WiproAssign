package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Helper.LoggerHelper;
import Helper.ScrollUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductDetail {

	@FindBy(id = "add-to-cart-button")
	public WebElement addTobag;
	String id = "add-to-cart-button";
	@FindBy(name = "OnePlus 138.8 cm (55 inches) Q1 Series 4K Certified Android QLED TV 55Q1IN-1 (Black) (Without Stand)")
	public static WebElement productDetail;
	
	@FindBy(xpath="//android.widget.EditText[@text='rupees 69,899']")
	public static WebElement price;
	
	@FindBy(className = "android.view.View")
	public WebElement popupPane;

	@FindBy(xpath = "//android.widget.TextView[@text='Added to cart']")
	public WebElement addedText;

	@FindBy(id = "a-autoid-1-announce")
	public WebElement cartBtn;
	ScrollUtil scroll;
	private final Logger log = LoggerHelper.getLogger(ProductDetail.class);
	
	public ProductDetail(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void addToCart(AndroidDriver<AndroidElement> driver) {

		String text = addTobag.getText();
		scroll = new ScrollUtil(driver);
		scroll.scrollToText(text);
		addTobag.click();
		log.info("Item Added to cart");

	}

	public static WebElement getProductName() {
		return productDetail;
	}

	public static WebElement getProductPrice()
	{
		return price;
	}
	public void addtoCartPopup() {
		boolean pop = popupPane.isDisplayed();
		log.info("Add to cart popup appears");
		if (pop) {
			log.info("Click on cart button");
			cartBtn.click();
		}
		else
		{
			log.error("Item not added successfully");
		}

	}

}
