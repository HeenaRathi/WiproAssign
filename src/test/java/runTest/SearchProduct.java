package runTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import Helper.ExcelReader;
import Helper.LoggerHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.base;

public class SearchProduct extends base {

	ExcelReader read = new ExcelReader();
	ArrayList<String> li = new ArrayList<>();
	private final Logger log = LoggerHelper.getLogger(SearchProduct.class);

	@Test
	public void searchProductList() throws IOException, InterruptedException {
			HomePage page = new HomePage(driver);
		page.clickSearchTab();
		Properties prop = loadPropertiesFile();
		String time = prop.getProperty("implicittime");
		String sheetName = prop.getProperty("sheetName"); //getting sheetname from properties
		page.impliciteWait(Integer.parseInt(time));
		try {
			li = read.getData(sheetName, "searchText"); // getting testData
			page.searchText(li.get(0)); // searching
			page.scrollList(driver, li.get(0));
			log.info("Get the item and navigate to description page");
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}

	}
}
