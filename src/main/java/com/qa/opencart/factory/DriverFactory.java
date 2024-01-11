package com.qa.opencart.factory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exception.OpencartAppException;
import com.qa.opencart.utils.PropUtil;

public class DriverFactory {

	public WebDriver driver = null;
	public OptionsManager optionsManager = null;

	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(String browserName) {
		// public WebDriver initDriver() {
		//
		optionsManager = new OptionsManager();
		System.out.println("browserName:>>> " + browserName);

		if (browserName == null) {
			if (Boolean.parseBoolean(PropUtil.getProperty("remote")) == true) {
				System.out.println("BROWSER NAME:: " + browserName);
				initRemoteDriver(browserName);
			} else {
				driver = new ChromeDriver(optionsManager.getChromeOptions());
				threadLocalDriver.set(driver);
			}
		} else {
			switch (browserName) {
			case "chrome":
				if (Boolean.parseBoolean(PropUtil.getProperty("remote")) == true) {
					System.out.println("BROWSER NAME:: " + browserName);
					initRemoteDriver(browserName);
				} else {
					driver = new ChromeDriver(optionsManager.getChromeOptions());
					threadLocalDriver.set(driver);
				}
				break;

			case "firefox":
				if (Boolean.parseBoolean(PropUtil.getProperty("remote")) == true) {
					initRemoteDriver(browserName);
				} else {
					driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
					threadLocalDriver.set(driver);
				}
				break;

			case "edge":
				if (Boolean.parseBoolean(PropUtil.getProperty("remote")) == true) {
					initRemoteDriver(browserName);
				} else {
					driver = new EdgeDriver(optionsManager.getEdgeOptions());
					threadLocalDriver.set(driver);
				}
				break;

			default:
				System.out.println("Given browser name is valid");
				throw new OpencartAppException("Entered browser name is not valid");
			}
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
//		getDriver().get(getProp("url"));
		getDriver().get(PropUtil.getProperty("url"));
		return driver;
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "\\screenshots\\" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		System.out.println(path);
		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * Run tests on grid.
	 * 
	 * @param browserName
	 */
	private void initRemoteDriver(String browserName) {
		System.out.println("Running tests on Grid with browser :" + browserName);
		try {
			switch (browserName.trim().toLowerCase()) {

			case "chrome":
				driver = new RemoteWebDriver(new URL(PropUtil.getProperty("remote-huburl")),
						optionsManager.getChromeOptions());
				threadLocalDriver.set(driver);
				break;
			case "firefox":
				driver = new RemoteWebDriver(new URL(PropUtil.getProperty("remote-huburl")),
						optionsManager.getFireFoxOptions());
				threadLocalDriver.set(driver);
				break;
			case "edge":
				driver = new RemoteWebDriver(new URL(PropUtil.getProperty("remote-huburl")),
						optionsManager.getEdgeOptions());
				threadLocalDriver.set(driver);
				break;
			default:
				System.out.println("wrong browser info " + browserName + " ,so it can't run on grid remote machine.");
				break;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
