package pageObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {
	  public static AppiumDriverLocalService service;
	  public static AndroidDriver<AndroidElement>  driver;
	  public static Properties OR;
	  public File f1;
		public FileInputStream file;
		
		
	
	public AppiumDriverLocalService startServer()
	{
		//
	boolean flag=	checkIfServerIsRunnning(4723);
	if(!flag)
	{
		
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
		return service;
		
	}
	
public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
public static void startEmulator() throws IOException, InterruptedException
{

	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
	Thread.sleep(6000);
}

	public static  AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{
		
FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resource\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
	

		// TODO Auto-generated method stub
	 File appDir = new File("src");
     File app = new File(appDir, (String) prop.get(appName));
     DesiredCapabilities capabilities = new DesiredCapabilities();
  String device=(String) prop.get("device");
    //String device= System.getProperty("deviceName");
    System.out.println(device);
  if(device.contains("emulator"))
  {
  startEmulator();
  }
     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
    
     capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
     capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
     
     capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    return driver;
	}
	
	public static void getScreenshot(String s) throws IOException
	{
	File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\"+s+".png"));
	
	}
	public void impliciteWait(long time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	public WebElement waitForElement(AndroidDriver<WebElement> driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public Properties loadPropertiesFile() throws IOException{
		
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		OR = new Properties();
		f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\resource\\global.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		return OR;
	}	

}
