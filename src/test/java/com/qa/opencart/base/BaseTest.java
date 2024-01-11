package com.qa.opencart.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.qa.opencart.factory.DriverFactory;
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
	
	@Parameters({"browser"})
	@BeforeTest
	public  void setUp(@Optional("chrome") String browserName) {
		driverFactory=new DriverFactory();
		
//		if(browserName !=null) {
//			PropUtil.setProperty("browser", browserName);
//			System.out.println("browser name:::> " + PropUtil.getProperty("browser"));
//		}
//		driver=driverFactory.initDriver(PropUtil.getProperty("browser"));
		
		if(browserName==null) {
			browserName=PropUtil.getProperty("browser");
		}
		driver=driverFactory.initDriver(browserName);
		System.out.println("BROWSER:::  "+ browserName);
		loginPage=new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
