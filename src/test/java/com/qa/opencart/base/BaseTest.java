package com.qa.opencart.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.PropUtil;

public class BaseTest {
	
	DriverFactory driverFactory;
	protected WebDriver  driver;
	protected LoginPage loginPage;
	protected AccountsPage accountPage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	
	protected SoftAssert softAssert;
	
	@Parameters("browser")
	@BeforeTest
	public  void setUp(String browser) {
		
		if(DriverFactory.getProp(browser)!=null) {
			PropUtil.initProp();
		}
		
		driverFactory=new DriverFactory();
		driver=driverFactory.initDriver(browser);
		loginPage=new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
