package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultPage {
	private WebDriver  driver=null;
	private ElementUtils eleUtil=null;

	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}
	
	//locator
	By prodList=By.xpath("//div[@class='caption']/h4/a");
	
	
	//action methods
	public ProductInfoPage selectProduct(String selectProd) {
		List<WebElement> productList=eleUtil.waitForVisibilityOfElements(prodList,AppConstants.MEDIUM_DEFAULT_WAIT);
		for(WebElement ele:productList) {
			String prodName=ele.getText();
			if(prodName.equals(selectProd)) {
				ele.click();
				System.out.println("click on "+ selectProd);
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	

}
