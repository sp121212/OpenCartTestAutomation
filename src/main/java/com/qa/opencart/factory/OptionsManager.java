package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.utils.PropUtil;

public class OptionsManager {
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
		if(Boolean.parseBoolean(PropUtil.getProperty("headless"))==true) {
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(PropUtil.getProperty("incognito"))==true) {
			co.addArguments("--incognito");
		}
		
		
		if(Boolean.parseBoolean(PropUtil.getProperty("remote"))==true) {
			co.setCapability("browserName", "chrome");
//			co.setBrowserVersion(PropUtil.getProperty("browserversion".trim()));
		}
		
		return co;
	}
	
	
	public FirefoxOptions getFireFoxOptions() {
		fo=new FirefoxOptions();
		if(Boolean.parseBoolean(PropUtil.getProperty("headless"))==true) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(PropUtil.getProperty("incognito"))==true) {
			fo.addArguments("--incognito");
		}
		
		if(Boolean.parseBoolean(PropUtil.getProperty("remote"))==true) {
			fo.setCapability("browserName", "firefox");
//			co.setBrowserVersion(PropUtil.getProperty("browserversion".trim()));
		}

		return fo;
	}
	
	
	public EdgeOptions getEdgeOptions() {
		eo=new EdgeOptions();
		if(Boolean.parseBoolean(PropUtil.getProperty("headless"))==true) {
			eo.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(PropUtil.getProperty("incognito"))==true) {
			eo.addArguments("--incognito");
		}
		
		return eo;
	}

}
