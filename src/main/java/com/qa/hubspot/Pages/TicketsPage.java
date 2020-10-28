package com.qa.hubspot.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Util.ElementUtil;

public class TicketsPage extends BasePage{

	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By createTicket = By.xpath("//span[text()='Create ticket']");
	By tktSub = By.xpath("//textarea[@data-field='subject']");
	
	By header = By.xpath("//span[text() = 'Tickets']");
	By createBtn = By.xpath("//button[@data-selenium-test='base-dialog-confirm-btn']");
	By backBtn = By.xpath("(//i18n-string[@data-key='profileHeader.backButton.TICKET'])[2]");
	
	public TicketsPage(WebDriver driver) 
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	public void createTicket(String ticketName)
	{
		elementUtil.waitForElementToBeVisible(createTicket, 10);
		elementUtil.doClick(createTicket);
		
		elementUtil.doSendKeys(tktSub, ticketName);
		elementUtil.waitForElementToBeVisible(createBtn, 5);
		elementUtil.doActionsClick(createBtn);
		
		elementUtil.waitForElementWithFluentWait(backBtn, 15, 3);
		elementUtil.doClick(backBtn);
	}
	
	public String verifyHeader()
	{
		return elementUtil.doGetText(header);
	}
	
	public String  getPageTitle()
	{
		return elementUtil.waitForPageTitlePresent("Tickets", 10);
		
	}
}
