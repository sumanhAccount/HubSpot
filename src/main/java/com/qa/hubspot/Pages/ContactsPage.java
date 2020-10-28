package com.qa.hubspot.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Util.ElementUtil;

public class ContactsPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	
	By label = By.cssSelector("span.private-dropdown__item__label");
	By CreateContact = By.xpath("//span[text()='Create contact']");
	//By mainMenuContact = By.xpath("(//a[@id='nav-primary-contacts-branch'])[1]");
	By email = By.xpath("//input[@data-field='email']");
	By fname = By.xpath("//input[@data-field='firstname']");
	By lname = By.xpath("//input[@data-field='lastname']");
	By JobTitle = By.xpath("//textarea[@data-field='jobtitle']");
	By secCreate= By.xpath("//button[@data-confirm-button='accept']");
	                     //(//span[text()='Create contact'])[position()=2]
	
	//By phone = By.xpath("//textarea[@data-field='phone']");
	
	
	By backToContact= By.xpath("(//span[contains(@class,'private-breadcrumbs__arrow--back')])[2]");
	
	By contactName = By.xpath("//span[@data-selenium-test='highlightTitle']");
	
	public ContactsPage(WebDriver driver) 
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String  verifyContactHeader()
	{   
		return elementUtil.doGetText(label);
	}
	
	public String  verifyContactPageTitle()
	{
		return elementUtil.waitForPageTitlePresent("Contacts", 20);
	}
	
	//public void createContact(String eid, String fn, String ln, String jt, String pno)
	public void createContact(String emailid, String firstname, String lastname, String jobtitle)
	{
		elementUtil.waitForElementToBeLocated(CreateContact, 20);
		elementUtil.doClick(CreateContact);
		
		elementUtil.waitForElementToBeLocated(email, 10);
		elementUtil.doSendKeys(email, emailid);
		 
		elementUtil.doSendKeys(fname, firstname);
		elementUtil.doSendKeys(lname, lastname);
		
		elementUtil.waitForElementToBeVisible(JobTitle, 5);
		elementUtil.doSendKeys(JobTitle, jobtitle);
		
		elementUtil.clickWhenReady(secCreate, 10);
		
		elementUtil.waitForElementToBeLocated(backToContact, 10);
		elementUtil.doClick(backToContact);
		
	}
	
	public String contactCreated()
	{
		return elementUtil.doGetText(contactName);
	}
	
	
	
}
