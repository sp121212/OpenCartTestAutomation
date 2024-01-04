package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtils eleUtil=null;

	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtils(driver);
	}

	//
	//locators
	By accHeadersLocators=By.xpath("//div[@id='content']/h2");
	By logoutLink=By.linkText("Logout");
	By search=By.xpath("//input[@name='search']");
	By searchText=By.cssSelector("input[type='text']");
	By searchButton=By.xpath("//span[@class='input-group-btn']");

	public String getAccPageTitle() {
		return eleUtil.waitForTitles(AppConstants.ACC_PAGE_TITLE,AppConstants.MEDIUM_DEFAULT_WAIT);
	}


	public String getAccPageURL() {
		return eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL,AppConstants.MEDIUM_DEFAULT_WAIT);
	}


	public boolean isLogoutLinkExit() {
		return eleUtil.waitForVisibilityOfElement(logoutLink,AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}

	public boolean isSearchFieldExit() {
		return eleUtil.waitForVisibilityOfElement(search,AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}


	public List<String> getAccountHeaders() {
		List<WebElement> headersEles=eleUtil.waitForVisibilityOfElements(accHeadersLocators,AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersList=new ArrayList<>();
		for(WebElement ele:headersEles) {
			headersList.add(ele.getText());
		}
		return headersList;
	}

	
	public SearchResultPage searchProduct(String searchProduct) {
		eleUtil.doClear(this.searchText);
		eleUtil.doSendKeysWithWait(this.searchText,AppConstants.MEDIUM_DEFAULT_WAIT,searchProduct);
		eleUtil.doClickWithWait(this.searchButton, AppConstants.MEDIUM_DEFAULT_WAIT);
		return new SearchResultPage(driver); //TDD Test Driven Development
	}
	
}
