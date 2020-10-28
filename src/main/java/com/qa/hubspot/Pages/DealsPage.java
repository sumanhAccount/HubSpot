package com.qa.hubspot.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Util.Constants;
import com.qa.hubspot.Util.ElementUtil;

public class DealsPage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	
	public DealsPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//By Sales = By.xpath("//*[@id='nav-primary-sales-branch']");  //click
	//By Deals = By.xpath("(//*[@id='nav-secondary-deals'])[1]");    //click
	By DealsHeader = By.xpath("(//span[@class='private-dropdown__item__label'])[1]"); //click
	
	By CreateDeal = By.xpath("//span[text()='Create deal']");
	
	By DealName = By.xpath("//textarea[@data-field='dealname']"); //send keys
	By DealsStage = By.xpath("@class='uiDropdown__buttonContents private-dropdown__button__contents'])[10]"); //dropdown
	
	By CreateFinal = By.xpath("//span[text()='Create']");
	
	By DealsBack = By.xpath("(//i18n-string[@data-key='profileHeader.backButton.DEAL'])[2]");
	
	
	
	public String getPageTitle()
	{
	return elementUtil.waitForPageTitlePresent(Constants.DEALS_PAGE_HEADER, 10);
		
	}
	public String  VerifyHeader() {
		return elementUtil.doGetText(DealsHeader);
	}
	
	
	public void createDeals()
	{  
		elementUtil.doClick(CreateDeal);
		elementUtil.waitForElementToBeVisible(DealName, 10);
		elementUtil.doSendKeys(DealName, "Dhruva");
		//elementUtil.doDropDownSelectByIndex(DealsStage, 3);
		elementUtil.waitForElementToBeVisible(CreateFinal, 5);
		elementUtil.doClick(CreateFinal);
		elementUtil.waitForElementToBeLocated(DealsBack, 10);
		elementUtil.doClick(DealsBack);
		
	}
	
}
