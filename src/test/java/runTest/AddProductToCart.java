package runTest;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import Helper.LoggerHelper;
import pageObject.ProductDetail;
import pageObject.base;

public class AddProductToCart extends base{
	
	ProductDetail prod;
	private final Logger log = LoggerHelper.getLogger(AddProductToCart.class);
	@Test
	public void addingProduct()
	{
		prod=new ProductDetail(driver);
		prod.addToCart(driver);
		prod.addtoCartPopup();
		log.info("Adding product to cart");
		Properties prop;
		try {
			prop = loadPropertiesFile();
			String time = prop.getProperty("implicittime");
			impliciteWait(Integer.parseInt(time));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
