package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class RegistrationPage {
	
	WebDriver driver;
	ElementUtils eleUtils;
	
	public RegistrationPage(WebDriver driver){
		this.driver=driver;
		eleUtils=new ElementUtils(driver);
	}
	
	//private locators
	private By firstNameLoc=By.xpath("//input[@name='firstname']");
	private By lastNameLoc=By.xpath("//input[@name='lastname']");
	private By emailLoc=By.xpath("//input[@name='email']");
	private By telephoneLoc=By.xpath("//input[@name='telephone']");
	private By passwordLoc=By.xpath("//input[@name='password']");
	private By confirmPasswordLoc=By.xpath("//input[@name='confirm']");
	private By isSubscribeNoLoc=By.xpath("//label[@class='radio-inline']/input[@value='0'] ");
	private By isSubscribeYesLoc=By.xpath("//label[@class='radio-inline']/input[@value='1'] ");
	private By isPrivacyPolicyCheckLoc=By.xpath("//input[@type='checkbox']");
	private By continueButtonLoc=By.xpath("//input[@value='Continue']");
	
	private By successMsg=By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registrerLink=By.linkText("Register");
	
	
	public boolean fillRegistrationPage(String fn,String ln,String ph, String email, String pass,String confirmPass,boolean isSubScribe,boolean isPrivacyPolicyChecked) {
		
		eleUtils.doclearSendKeysWithWait(firstNameLoc,AppConstants.MEDIUM_DEFAULT_WAIT, fn);
		eleUtils.doClearSendkeys(lastNameLoc,ln);
		eleUtils.doClearSendkeys(emailLoc,email);
		eleUtils.doClearSendkeys(telephoneLoc,ph);
		eleUtils.doClearSendkeys(passwordLoc,pass);
		eleUtils.doClearSendkeys(confirmPasswordLoc, confirmPass);
//		boolean isSubScribe=true;
		if(isSubScribe) {
			eleUtils.doClick(isSubscribeYesLoc);
		}else {
			eleUtils.doClick(isSubscribeNoLoc);
		}
		
//		boolean isPrivacyPolicyChecked=true;
		if(isPrivacyPolicyChecked) eleUtils.doClick(isPrivacyPolicyCheckLoc);
		
		
		eleUtils.doClick(continueButtonLoc);
		
		String successMessage=eleUtils.getTextWithWait(successMsg,AppConstants.SHORT_DEFAULT_WAIT);
		if(successMessage.equals(AppConstants.REG_SUCCESS_MESSAGE)) {
			eleUtils.doClickWithWait(logoutLink, AppConstants.MEDIUM_DEFAULT_WAIT);
			eleUtils.doClickWithWait(registrerLink, AppConstants.MEDIUM_DEFAULT_WAIT);
			return true;
		}else {
			return false;
		}
		
		
	}
	

}
