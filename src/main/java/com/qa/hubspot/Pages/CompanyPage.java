package com.qa.hubspot.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Util.ElementUtil;

public class CompanyPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	
	
	By header = By.xpath("//span[text() = 'Companies']");
	By createCompany = By.xpath("//span[text()='Create company']");
	By CompanyDomName= By.xpath("//input[@data-field='domain']");
	By compName = By.xpath("//textarea[@data-field='name']");
	By createCompanyBtn= By.xpath("//span[text()='Create company'][2]");
	
	
	
	public CompanyPage(WebDriver driver) 
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String verifyHeader()
	{
		return elementUtil.doGetText(header);
	}
	
	public String  getPageTitle()
	{
		return elementUtil.waitForPageTitlePresent("Companies", 10);
		
	}
	
	public void createCompany(String domName, String comName)
	{
		elementUtil.waitForElementToBeLocated(createCompany, 10);
		elementUtil.doClick(createCompany);
		
		elementUtil.waitForElementToBeLocated(CompanyDomName, 5);
		elementUtil.doSendKeys(CompanyDomName, domName);
		
		elementUtil.waitForElementToBeLocated(compName, 5);
		elementUtil.doSendKeys(CompanyDomName, comName);
		
		
		elementUtil.waitForElementToBeLocated(createCompanyBtn, 5);
		elementUtil.doClick(createCompanyBtn);
		
	}
	
}
