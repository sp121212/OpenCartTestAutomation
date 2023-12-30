package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.opencart.exception.OpencartAppException;

public class PropUtil {

	private  static Properties initProp() {
		FileInputStream input = null;
		String envName = System.getProperty("env");
		System.out.println("env name:" + envName);
		Properties	prop=new Properties();
		try {
			if (envName == null) {
				System.out.println("your env name is null, hence the tests are running on QA Env.");
				input = new FileInputStream("src\\test\\resources\\config\\config-qa.properties");
				System.out.println("qa propties is fetched");
			} else {
				switch (envName) {
				case "qa":
					input = new FileInputStream("src\\test\\resources\\config\\config-qa.properties");
					System.out.println("qa propties is fetched");
					break;

				case "staging":
					input = new FileInputStream("src\\test\\resources\\config\\config-stage.properties");
					System.out.println("staging propties is fetched");
					break;

				case "prod":
					input = new FileInputStream("src\\test\\resources\\config\\config-prod.properties");
					System.out.println("prod propties is fetched");
					break;
					
				case "uat":
					input = new FileInputStream("src\\test\\resources\\config\\config-uat.properties");
					System.out.println("uat propties is fetched");
					break;

				default:
					System.out.println("Wrong Env name:" + envName);
					throw new OpencartAppException("Wrong Env name:" + envName);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			
			prop.load(input);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	
	public static Properties prop = initProp();

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key, String value) {
		prop.setProperty(key, value);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
