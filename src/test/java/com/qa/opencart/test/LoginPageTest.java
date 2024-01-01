package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.utils.PropUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("Epic 100: Design Open cart login page.")
@Story("US 1010: Login page test script")
@Feature("Login page valid scenarios")
public class LoginPageTest extends BaseTest{
	
	
	@Description("Login page title test.")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actPageTitle=loginPage.getPageTitle();
		Assert.assertEquals(actPageTitle,AppConstants.LOGIN_PAGE_TITLE+"a");
	}
	
	
	@Description("Login page url test.")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actPageUrl=loginPage.getPageURL();
		Assert.assertTrue(actPageUrl.contains(DriverFactory.getProp("url")));
	}
	
	@Test(priority = 3)
	public void IsLogoAvaialbleTest() {
		Assert.assertTrue(loginPage.isLogoVailable());
	}
	
	
	@Description("Login page forgot link availblity test.")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void isForgotLinkAvailableTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkAvailable());
	}
	
	
	@Description("Login page login functionality test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void doLoginTest() {
		accountPage=loginPage.doLogin(PropUtil.getProperty("username"),PropUtil.getProperty("password"));
		System.out.println("\n---------------");
		System.out.println("user name :"+ PropUtil.getProperty("username"));
		System.out.println("password :"+ PropUtil.getProperty("password"));
		System.out.println("---------------\n");
		Assert.assertTrue(loginPage.isLogoutLinkPresent());
	}
}
