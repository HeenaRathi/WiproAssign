package pageObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Helper.ExcelReader;
import Helper.LoggerHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import runTest.CheckingOutProduct;

public class LoginPage extends base{

	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//Already a customer? Sign in
	@AndroidFindBy(xpath="//android.widget.Button[@text='Already a customer? Sign in']")
	public WebElement signIn;
	
	@AndroidFindBy(id="ap_email_login")
	public WebElement mobileField;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Continue']")
	public WebElement continueBtn;
	
	@AndroidFindBy(id="ap_password")
	public WebElement pswdBtn;
	
	@AndroidFindBy(id="signInSubmit")
	public WebElement loginBtn;
	
	ExcelReader read= new ExcelReader();
	ArrayList<String> list= new ArrayList<>();
	
	
	public void clickOnSign()
	{
		if(signIn.isEnabled())
		{
			signIn.click();
			log.info("Clicked on the Login ");
		}
		else
		{
			log.error("Could not click button is not enabled");
		}
	
	}
	
	public void signApp()
	{
		Properties prop= new Properties();
		try {
			prop=loadPropertiesFile();
			list=read.getData(prop.getProperty("sheetName"), "Mobile");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mobileField.sendKeys(list.get(0));
		continueBtn.click();
		pswdBtn.click();
		pswdBtn.sendKeys(list.get(1));
		loginBtn.click();
		log.info("Login successfully");
	}
	
	
}
