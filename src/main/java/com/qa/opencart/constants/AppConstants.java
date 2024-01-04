package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL="route=account/login";
	
	//test
	public static final String ACC_PAGE_TITLE="My Account";
	public static final String ACC_PAGE_URL="route=account/account";
	public static final int NUM_OF_HEADERS_IN_ACC_PAGE=4;
	public static final List<String> ACC_HEADERS=Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	
	
	public static final int SHORT_DEFAULT_WAIT=5;
	public static final int MEDIUM_DEFAULT_WAIT=10;
	public static final int LONG_DEFAULT_WAIT=15;
	
	
	public static final String APP_URL="https://naveenautomationlabs.com/opencart/index.php?route=account/login";
	public static final String USER_NAME="Santanup1212@gmail.com";
	public static final String PASSWORD="test@123";
	
	
	public static final String REG_SUCCESS_MESSAGE="Your Account Has Been Created!";
	
	
	
	
}
