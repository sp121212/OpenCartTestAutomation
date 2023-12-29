package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		accountPage=loginPage.doLogin(AppConstants.USER_NAME, AppConstants.PASSWORD);
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accountPage.getAccPageTitle(), AppConstants.ACC_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		String accPageUrl=accountPage.getAccPageURL();
		Assert.assertTrue(accPageUrl.contains(AppConstants.ACC_PAGE_URL));
	}

	@Test
	public void isLogoutLinkExitTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExit());
	}
	
	@Test
	public void isSearchFieldTest() {
		Assert.assertTrue(accountPage.isSearchFieldExit());
	}
	
	@Test
	public void accPageHeadersCountTest() {
		List<String> accHeaders=accountPage.getAccountHeaders();
		Assert.assertEquals(accHeaders.size(),AppConstants.NUM_OF_HEADERS_IN_ACC_PAGE);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accHeaders=accountPage.getAccountHeaders();
		Assert.assertEquals(accHeaders,AppConstants.ACC_HEADERS);
	}
	
	@Test
	public void searchProdTest() {
		searchResultPage=accountPage.searchProduct("MAC");
		searchResultPage.selectProduct("MacBook Pro");
	}
	
}
