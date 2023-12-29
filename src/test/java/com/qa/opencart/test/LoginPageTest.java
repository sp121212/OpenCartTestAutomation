package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.driverfactory.DriverFactory;

public class LoginPageTest extends BaseTest{
	
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actPageTitle=loginPage.getPageTitle();
		Assert.assertEquals(actPageTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actPageUrl=loginPage.getPageURL();
		Assert.assertTrue(actPageUrl.contains(DriverFactory.getProp("url")));
	}
	
	@Test(priority = 3)
	public void IsLogoAvaialbleTest() {
		Assert.assertTrue(loginPage.isLogoVailable());
	}
	
	@Test(priority = 4)
	public void isForgotLinkAvailableTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkAvailable());
	}
	
	@Test(priority = 5)
	public void doLoginTest() {
		accountPage=loginPage.doLogin(AppConstants.USER_NAME,AppConstants.PASSWORD);
		System.out.println("user name :"+ AppConstants.USER_NAME);
		System.out.println("password :"+ AppConstants.PASSWORD);
		Assert.assertTrue(loginPage.isLogoutLinkPresent());
	}
}
