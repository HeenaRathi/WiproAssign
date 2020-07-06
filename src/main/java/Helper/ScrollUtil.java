package Helper;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ScrollUtil {
	public AndroidDriver<AndroidElement> driver;

	public ScrollUtil(AndroidDriver<AndroidElement> driver2) {
		// TODO Auto-generated constructor stub
		this.driver = driver2;
	}

	public void scrollToText(String text) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
	}
}
