package com.qa.hubspot.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Util.Constants;
import com.qa.hubspot.Util.ElementUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	
	By emailId= By.id("username");
	By password= By.id("password");
	By login = By.id("loginBtn");
	By signUp= By.linkText("Sign up");
	By showPassword= By.xpath("(//button)[1]");
	By forgotPassword = By.linkText("Forgot my password");
	
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getPageTitle()
	{
	return elementUtil.waitForPageTitlePresent(Constants.LOGIN_TITLE, 10);
		
	}
	
	public boolean isSignUpPresent()
	{
		return elementUtil.doIsDisplayed(signUp);
	}
	
	public boolean isShowPasswordPresent()
	{
		return elementUtil.doIsDisplayed(showPassword);
	}

	public boolean isForgotPasswordPresent()
	{
		return elementUtil.doIsDisplayed(forgotPassword);
	}
	
	public HomePage doLogin(String username, String pwd)
	{
		elementUtil.doSendKeys(emailId, username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(login);
		return new HomePage(driver);
	}
	
}
