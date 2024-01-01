package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver=null;
	private ElementUtils eleUtil=null;

	//locators
	private By email=By.cssSelector("#input-email");
	private By pass=By.cssSelector("#input-password");
	private By login=By.cssSelector("input[type='submit']");
	private By forgotPassLink=By.linkText("Forgotten Password");
	private By logo=By.cssSelector("img[src*='logo.png']");
	private By logoutLink=By.linkText("Logout");
	private By registerLoc=By.linkText("Register");
	
	//page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}

	//page actions/methods

	@Step("Fetch the page title")
	public String getPageTitle() {
		return eleUtil.waitForTitles(AppConstants.LOGIN_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_WAIT);
	}

	@Step("Fetch the page url")
	public String getPageURL() {
		return eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL,AppConstants.MEDIUM_DEFAULT_WAIT);
	}

	@Step("Checking forgot password availability.")
	public boolean isForgotPasswordLinkAvailable() {
		return eleUtil.waitForVisibilityOfElement(forgotPassLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	@Step("Checking logo vaialblity")
	public boolean isLogoVailable() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	@Step("Checking logout link availability")
	public boolean isLogoutLinkPresent() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	
	@Step("Checking registerlink availability")
	public boolean isRegisterlinkPresent() {
		return eleUtil.waitForVisibilityOfElement(registerLoc, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	@Step("Navigte to registration page")
	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.doClickWithWait(registerLoc,AppConstants.MEDIUM_DEFAULT_WAIT);
		return new RegistrationPage(driver);
		
	}
	
	@Step("passing username: {0} and password as {1}")
	public AccountsPage doLogin(String userName,String password) {
		eleUtil.doSendKeysWithWait(email, AppConstants.MEDIUM_DEFAULT_WAIT, userName);
		eleUtil.doSendKeysWithWait(pass, AppConstants.MEDIUM_DEFAULT_WAIT,password);
		eleUtil.doClickWithWait(login, AppConstants.MEDIUM_DEFAULT_WAIT);
		return new AccountsPage(driver);
	}
}
