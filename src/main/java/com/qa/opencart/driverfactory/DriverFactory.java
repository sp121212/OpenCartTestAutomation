package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.exception.OpencartAppException;

public class DriverFactory {

	public WebDriver driver=null;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(String browserName) {
//	public WebDriver initDriver(String browserName) {
//		String browserName=getProp("browser");
//		String browserName=getProp("browser");
		System.out.println(browserName);
		
		switch(browserName) {
		case "chrome":
			driver=new ChromeDriver();
			tlDriver.set(driver);
			break;

		case "firefox":
			driver=new FirefoxDriver();
			tlDriver.set(driver);
			break;

		case "edge":
			driver=new EdgeDriver();
			tlDriver.set(driver);
			break;

		default:
			System.out.println("Given browser name is valid");
			throw new OpencartAppException("Entered browser name is not valid");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(getProp("url"));

		return driver;
	}


	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	

	private static  Properties initProp() {
		FileInputStream fis=null;
		Properties prop=new Properties();
		
		String envName=System.getProperty("env".trim().toLowerCase());
		try { 
		if(envName==null) {
			fis=new FileInputStream("src\\test\\resources\\config\\config.properties");
		}else {
			
			switch (envName) {
			case "qa":
				fis=new FileInputStream("src\\test\\resources\\config\\config-qa.properties");
				break;
			case "stage":
				fis=new FileInputStream("src\\test\\resources\\config\\config-stage.properties");
				break;
			case "uat":
				fis=new FileInputStream("src\\test\\resources\\config\\config-uat.properties");
				break;
			case "prod":
				fis=new FileInputStream("src\\test\\resources\\config\\config-prod.properties");
				break;

			default:
				System.out.println("env input value is incorrct, please provide a valid input!");
				throw new OpencartAppException(envName);
			}
		}
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getProp(String key) {
		Properties prop = null;
		try {
			prop = initProp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	
	public static String getScreenshot(String methodName) {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"\\screenshots\\"+methodName+"_"+System.currentTimeMillis()+".png";
		System.out.println(path);
		File destination=new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return path;
	}
}
