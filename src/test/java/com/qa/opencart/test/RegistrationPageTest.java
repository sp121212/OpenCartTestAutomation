package com.qa.opencart.test;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{


	@BeforeClass
	public void setUpRegistartionPage() {
		registrationPage=loginPage.navigateToRegistrationPage();
	}
	
	private String randomEmailGenerator() {
		return "test"+System.currentTimeMillis()+"@bllc.com";
	}
	
	@DataProvider
	public Object[][] testData() {
		Object[][] testdata= {
				{"Amitabh","Mumbai","022-9090323432",randomEmailGenerator(),"Amitabh123","Amitabh123",true,true},
				{"Mithun","Kolkata","033-5447898",randomEmailGenerator(),"Mithun123","Mithun123",false,true},
				{"Rajnikant","Chennai","044-23215",randomEmailGenerator(),"john123","john123",true,true},
				{"Sarukh","Delhi","011-9090323432",randomEmailGenerator(),"john123","john123",false,true},
				};
		
		return testdata;
	}
	
	
	@DataProvider
	public Object[][] getDataFromExcel() {
		Object[][] testExcelData=ExcelUtil.getTestData("Sheet1");
		
		return testExcelData;
	}
	
	
	
	@Test(dataProvider="getDataFromExcel")
	public void fillRegistrationLink(String fn,String ln,String ph,String pass,String isSubScribe) {
	boolean flag;
		if(isSubScribe.equals("yes")) {
			flag=true;
		}else {
			flag=false;
		}
		registrationPage.fillRegistrationPage(fn,ln,ph,randomEmailGenerator(),pass,pass,flag,true);
	}
	
}
