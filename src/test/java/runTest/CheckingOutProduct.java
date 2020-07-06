package runTest;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import Helper.LoggerHelper;
import pageObject.CheckOut;
import pageObject.base;

public class CheckingOutProduct extends base {
	
	private final Logger log = LoggerHelper.getLogger(CheckingOutProduct.class);
	CheckOut check;
	
	@Test
	public void checkOutProduct()
	{
		check= new CheckOut(driver);
		check.validateProductDetails();
		check.proceedTobuy();
		Properties prop;
		try {
			prop = loadPropertiesFile();
			String time = prop.getProperty("implicittime");
			impliciteWait(Integer.parseInt(time));
			check.selectAddress();
			check.selectDeliveryOption();
			impliciteWait(Integer.parseInt(time));
			check.cashOndeliv(driver);
			log.info("Order Placed");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

}
