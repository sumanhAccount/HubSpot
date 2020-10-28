package com.qa.hubspot.Test;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Pages.HomePage;
import com.qa.hubspot.Pages.LoginPage;


public class HomePageTest {
	WebDriver driver;
	BasePage basePage;
	LoginPage loginpage;
	Properties prop;
	 HomePage homepage;
	 
	 @Parameters("browser")
	@BeforeTest
	public void setUp()
{
		basePage =  new BasePage();
		
		prop = basePage.init_prop();
	driver = basePage.init_driver(prop);
	loginpage= new LoginPage(driver);
	driver.navigate().to("https://app.hubspot.com/login");
	homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
}
	@Test (priority=2)
	public void verifyHeader()
	{
		String heading = homepage.heading();
		Assert.assertEquals(heading, "Setup Guide");
	}
	
	@Test(priority=1)
	public void verifySettingIconPresent()
	{
		boolean val = homepage.isSettingIcon();
		Assert.assertTrue(val);
	}
	
	@Test
	public void listofCatagory()
	{
		homepage.listOfSubCats();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
