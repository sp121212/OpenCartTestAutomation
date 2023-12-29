package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.opencart.exception.OpencartAppException;

public class PropUtil {

	public static Properties initProp() {
		FileInputStream input = null;
		Properties prop = null;

		String envName = System.getProperty("env");
		System.out.println("env name:" + envName);

		try {
			if (envName == null) {
				System.out.println("your env name is null, hence the tests are running on QA Env.");
				input = new FileInputStream("./src/test/resources/config/config-qa.properties");
			} else {
				switch (envName) {
				case "qa":
					input = new FileInputStream("./src/test/resources/config/config-qa.properties");
					break;
					
				case "staging":
					input=new FileInputStream("./src/test/resources/config/config-stage.properties");
					break;
					
				case "prod":	
					input=new FileInputStream("./src/test/resources/config/config-prod.properties");
					break;
				case "uat":	
					input=new FileInputStream("./src/test/resources/config/config-uat.properties");
					break;
					
				default:
					System.out.println("Wrong Env name:"+ envName);
					throw new OpencartAppException("Wrong Env name:"+ envName);
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

}
