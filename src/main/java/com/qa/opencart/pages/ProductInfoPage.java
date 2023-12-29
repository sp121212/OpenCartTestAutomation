package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {


	private WebDriver driver;
	private ElementUtils eleutil;
	Map<String,String> prodInfoDetails=new TreeMap<String,String>();



	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtils(driver);
	}

	//locator
	private By prodHeaderNameLoc=By.cssSelector("div#content h1");
	private By imagesCountLoc=By.cssSelector("div#content ul.thumbnails li");
	private By productMetadataLoc=By.xpath("//div[@id='content']//ul[@class='list-unstyled'] [1]/li");
	private By prodcutPriceLoc=By.xpath("//div[@id='content']//ul[@class='list-unstyled'] [2]/li/h2");
	private By prodcutPriceExTaxLoc=By.xpath("//div[@id='content']//ul[@class='list-unstyled']/li[contains(text(),'Ex Tax:')]");

	//public action methods

	//prod header name
	public String getProductName() {
		return eleutil.getTextWithWait(prodHeaderNameLoc, AppConstants.MEDIUM_DEFAULT_WAIT);
	}

	//image count
	public int getImagesCount() {
		return eleutil.waitForVisibilityOfElements(imagesCountLoc, AppConstants.MEDIUM_DEFAULT_WAIT).size();
	}

	//get prod meta data
	public void getProductMetaData() {
		List<WebElement> prodMetaData= eleutil.waitForVisibilityOfElements(productMetadataLoc, AppConstants.MEDIUM_DEFAULT_WAIT);

		for(WebElement ele:prodMetaData) {
			String[] prodInfo=ele.getText().split(":");
			prodInfoDetails.put(prodInfo[0].trim(), prodInfo[1].trim());
		}
	}

	//get prod price data
	public void getProductPrice() {
		String prodPrice= eleutil.waitForVisibilityOfElement(prodcutPriceLoc, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		prodInfoDetails.put("price",prodPrice.trim()) ;
		
		String[] prodPriceEx=eleutil.waitForVisibilityOfElement(prodcutPriceExTaxLoc, AppConstants.MEDIUM_DEFAULT_WAIT).getText().split(":");
		prodInfoDetails.put(prodPriceEx[0].trim(),prodPriceEx[1].trim());
	}



	// get all prod details
	public Map<String, String> getAllProdInfo() {
		
		prodInfoDetails.put("prodName",getProductName());
		getProductPrice();
		getProductMetaData();
		
		
		return prodInfoDetails;
		
		
	}



}
