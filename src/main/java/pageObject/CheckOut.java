package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Helper.LoggerHelper;
import Helper.ScrollUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOut extends base {

	@FindBy(xpath = "//android.widget.TextView[@text='OnePlus 138.8 cm (55 inches) Q1 Series 4K Certified Android QLED...']")
	public WebElement itemAdded;

	@FindBy(xpath = "//android.widget.Button[@text='Proceed to Buy']")
	public WebElement buyBtn;

	@FindBy(id = "a-autoid-0-announce")
	public WebElement addressBtn;

	@FindBy(xpath = "//android.widget.Button[@text='Continue']")
	public WebElement contBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Pay on Delivery']")
	public WebElement payOndelivery;

	@FindBy(id = "pp-y0v5Hb-287")
	public WebElement finalSubmit;

	@FindBy(xpath = "//android.widget.TextView[10]")
	public WebElement prodPrice;

	private final Logger log = LoggerHelper.getLogger(CheckOut.class);

	public CheckOut(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void validateProductDetails() {
		WebElement productName = ProductDetail.getProductName();
		WebElement price = ProductDetail.getProductPrice();
		if (itemAdded.getText().equals(productName) || prodPrice.getText().equals(price)) {
			Assert.assertEquals(itemAdded, productName);
			Assert.assertEquals(price, prodPrice);
			log.info("Item matches");

		} else {
			log.error("Item did not matches");
			Assert.assertFalse(false);

		}
	}

	public void proceedTobuy() {
		log.info("Click on buy button");
		buyBtn.click();

	}

	public void selectAddress() {
		log.info("Selecting address");
		if (addressBtn.isEnabled()) {
			addressBtn.click();
		} else {
			log.error("Button is not enabled");
		}
	}

	public void selectDeliveryOption() {
		contBtn.click();
	}

	public void cashOndeliv(AndroidDriver<AndroidElement> driver) {
		ScrollUtil scroll = new ScrollUtil(driver);
		scroll.scrollToText(payOndelivery.getText());
		payOndelivery.click();
		finalSubmit.click();
		log.info("Order Placed");
	}
}
