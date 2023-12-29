package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void prodInfoPageSetup() {
		accountPage=loginPage.doLogin(AppConstants.USER_NAME, AppConstants.PASSWORD);
	}

	
	@DataProvider
	public Object[][] testData() {
		return	new Object[][] {
			{"Mac","MacBook Pro"},
			{"Mac","MacBook"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"iPod","iPod Classic"},
			{"Canon","Canon EOS 5D"}
		};
		
	}
	
	
	
	
	
	@Test(dataProvider="testData")
	public void prodInfoTest(String searchProd, String selectProd) {
		searchResultPage=accountPage.searchProduct(searchProd);
		productInfoPage=searchResultPage.selectProduct(selectProd);
		
		Map<String,String> prodDetails=productInfoPage.getAllProdInfo();
		System.out.println(prodDetails);
//		softAssert.assertEquals(prodDetails.get("Brand"), "Apple");
//		softAssert.assertEquals(prodDetails.get("Availability"), "In Stock");
//		softAssert.assertEquals(prodDetails.get("price"), "$2,000.00");
//		softAssert.assertEquals(prodDetails.get("Ex Tax"), "$2,000.00");
//		softAssert.assertEquals(prodDetails.get("prodName"), "MacBook Pro");
//		
//		softAssert.assertAll();
		
	}
}
