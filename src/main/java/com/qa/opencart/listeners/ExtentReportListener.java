package com.qa.opencart.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.driverfactory.DriverFactory;

public class ExtentReportListener implements ITestListener {
	public static final String OUTPUT_FOLDER = "./reports";
	public static final String FILE_NAME = "/TestExecutionReport.html";

	private static ExtentReports extent = init();
	private static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	private static ExtentReports init() {
		extentReports = new ExtentReports();
		Path path = Paths.get(OUTPUT_FOLDER);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
			reporter.config().setReportName("OPEN CART AUTOMATION TEST RESULT");

			extentReports.attachReporter(reporter);
			extentReports.setSystemInfo("System", "Windows");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return extentReports;

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("TestSuite is started.");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("TestSuite is ended.");
		extent.flush();
		test.remove();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		String methodName=result.getMethod().getMethodName();
		String qualifiedName=result.getMethod().getQualifiedName();
		int last=qualifiedName.lastIndexOf(".");
		int mid=qualifiedName.substring(0, last).lastIndexOf(".");
		String className=qualifiedName.substring(mid+1,last);
		
		
		System.out.println(methodName+" started.!");
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " passed!"));
		test.get().pass("Test passed");
		//test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		String methodName = result.getMethod().getMethodName();
		test.get().fail("Test failed");
		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		String methodName = result.getMethod().getMethodName();
		
		test.get().skip("Test skipped");

		//test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	
	private Date getTime(long milli) {
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(milli);
		return cal.getTime();
	}
}
