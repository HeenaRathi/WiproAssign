package runTest;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.LoginPage;
import pageObject.base;

public class Login extends base {
	
	@BeforeTest
	public void InvokeServer() throws IOException, InterruptedException
	{
		service=startServer();
		AndroidDriver<AndroidElement> driver=capabilities("GeneralStoreApp");
	}
	
	@Test
	public void signIn() throws IOException, InterruptedException
	{
		
		LoginPage log= new LoginPage(driver);
		log.clickOnSign();
	}

}
