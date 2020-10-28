package com.qa.hubspot.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Util.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	
	By header= By.xpath("//h1");
	By search = By.xpath("//input[@id='navSearch-input']");
	By settingIcon = By.xpath("//a[@id='navSetting']");
	By subCat = By.xpath("(//h4)");
	By primaryContact = By.xpath("(//a[@id='nav-primary-contacts-branch'])[position()=1]");
	By SecContact = By.xpath("(//a[@id='nav-secondary-contacts'])[1]");
	
	By Sales = By.xpath("//*[@id='nav-primary-sales-branch']"); 
	
	By Deals = By.xpath("(//*[@id='nav-secondary-deals'])[1]");    
	
	///***************Tickets creating webelements***////////////
	
	By Services = By.xpath("(//*[@id='nav-primary-service-branch'])[1]");
	By Tickets = By.xpath("(//*[@id='nav-secondary-tickets'])[1]");
	
	
	///***************Comany Creating WebElements*******///////////
	
	
	By companiesMenu= By.xpath("(//a[@id='nav-secondary-companies'])[1]");
	By ContactsMenu = By.xpath("(//a[@id='nav-primary-contacts-branch'])[1]");

	
	
public HomePage(WebDriver driver)
{
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
}

public boolean isSearchIcon()
{
	return elementUtil.doIsDisplayed(search);
}

public boolean isSettingIcon() {
return 	elementUtil.doIsDisplayed(settingIcon);
}

public String heading()
{
	return elementUtil.doGetText(header);
}

public void listOfSubCats()
{
	List<WebElement> sub= driver.findElements(subCat);
	int size= sub.size();
	
	for(int i=0;i<size;i++)
	{
		String temp =sub.get(i).getText();
		System.out.println(temp);
	}

}

	public ContactsPage gotoPrimaryContact()
	{
		clickonSecondContact();
		return new ContactsPage(driver);
	}
	  
	
	
	private void clickonSecondContact()
	{
		elementUtil.waitForElementToBeLocated(primaryContact, 10);
		elementUtil.doClick(primaryContact);
		
		elementUtil.waitForElementToBeLocated(SecContact, 10);
		elementUtil.doClick(SecContact);
	}
	
	private void clickonDeals()
	{
		elementUtil.waitForElementToBeLocated(Sales, 5);
		elementUtil.doClick(Sales);
		
		elementUtil.waitForElementToBeLocated(Deals, 5);
		elementUtil.doClick(Deals);
	}
	
public DealsPage gotoDeals()
{
	clickonDeals();
	driver.navigate().refresh();
	driver.navigate().refresh();
	return new DealsPage(driver);
}

private void  clickOnService()
{
	elementUtil.waitForElementToBeLocated(Services, 10);
	elementUtil.doClick(Services);
	
	
	elementUtil.waitForElementToBeLocated(Tickets, 5);
	elementUtil.doClick(Tickets);
}


public TicketsPage goToTickets()
{
	clickOnService();	
	return new TicketsPage(driver);
}

private void  clickOnCompanies()
{
	elementUtil.waitForElementToBeLocated(companiesMenu, 10);
	elementUtil.doClick(companiesMenu);
	
	
	elementUtil.waitForElementToBeLocated(ContactsMenu, 5);
	elementUtil.doClick(ContactsMenu);
}

public CompanyPage goToCompanies()
{
	clickOnCompanies();
	return new CompanyPage(driver);
}


}
