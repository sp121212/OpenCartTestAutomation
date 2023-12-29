package com.qa.opencart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH="src\\test\\resources\\testdata\\opencart-test.xlsx";
	private static Workbook book=null;
	private static Sheet sheet=null;

	public static Object[][] getTestData(String sheetName) {
		
		System.out.println("READ DATA FROM SHEET: "+sheetName);
		Object[][] testData=null;
		
		
		try {
			FileInputStream inputPath=new FileInputStream(TEST_DATA_SHEET_PATH);
			book=WorkbookFactory.create(inputPath);
			Sheet sheet=book.getSheet(sheetName);
			
			
			testData=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for(int i=0;i<sheet.getLastRowNum();i++) {
				
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) { 
					
					testData[i][j]=sheet.getRow(i+1).getCell(j).toString();
					
				}
			}

		}catch(FileNotFoundException fnoe) {
			fnoe.printStackTrace();
		}catch(IOException io) {
			io.printStackTrace();
		}
		return testData;
	}

}
