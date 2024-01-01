package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ElementUtils {
	private WebDriver driver=null;

	public ElementUtils(WebDriver driver) {
		this.driver=driver;
	}

	/*############################# GENERIC FUNCTIONS #####################################*/
	
	public void doClick(By locator) {
		driver.findElement(locator).click();
	}
	
	public void doClear(By locator) {
		driver.findElement(locator).clear();
	}

	public void doSendkeys(By locator,String inputString) {
		driver.findElement(locator).sendKeys(inputString);
	}
	
	public void doClearSendkeys(By locator,String inputString) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(inputString);
	}
	
	
	/*############################# WAIT FUNCTIONS #####################################*/

	public String  waitForTitles(String title,int timeOut) {
		String getTitle=null;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean flag= wait.until(ExpectedConditions.titleIs(title));
		if(flag) {
			getTitle=driver.getTitle();
		}
		return getTitle;
	}

	public String waitForURLContains(String url,int timeout) {
		String getURL=null;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		boolean flag= wait.until(ExpectedConditions.urlContains(url));
		if(flag) {
			getURL=driver.getCurrentUrl();
		}
		return getURL;
	}


	@Step("Waiting for visibility of element for locator{0} with timeout {1}")
	public WebElement waitForVisibilityOfElement(By locator,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public List<WebElement> waitForVisibilityOfElements(By locator,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}




	public WebElement waitForPresenceOfElement(By locator,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}






	public void doClickWithWait(By locator,int timeOut) {
		waitForVisibilityOfElement(locator,timeOut).click();
	}


	public void doSendKeysWithWait(By locator,int timeOut,String inputValue) {
		waitForVisibilityOfElement(locator,timeOut).sendKeys(inputValue);
	}
	
	public void doclearSendKeysWithWait(By locator,int timeOut,String inputValue) {
		WebElement ele=waitForVisibilityOfElement(locator,timeOut);
		ele.clear();
		ele.sendKeys(inputValue);
	}

	public String getTextWithWait(By locator,int timeOut) {
		return waitForVisibilityOfElement(locator,timeOut).getText();
	}

}
