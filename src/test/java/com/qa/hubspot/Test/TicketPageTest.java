package com.qa.hubspot.Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Pages.HomePage;
import com.qa.hubspot.Pages.LoginPage;
import com.qa.hubspot.Pages.TicketsPage;
import com.qa.hubspot.Util.Constants;
import com.qa.hubspot.Util.ExcelUtil;

public class TicketPageTest {
	
	
	WebDriver driver;
	BasePage basePage;
	LoginPage loginpage;
	Properties prop;
	HomePage homepage;
	TicketsPage ticketspage;

	@BeforeTest
	public void setUp()
{
	basePage =  new BasePage();
	prop = basePage.init_prop();
	driver = basePage.init_driver(prop);
	loginpage= new LoginPage(driver);
	homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	ticketspage = homepage.goToTickets();
}
	
	@Test(priority =1)
	public void verifyHeaderforTicket()
	{
		String title = ticketspage.verifyHeader();
		System.out.println("Page header is "+title);
	}
	
	@Test (priority =2)
	public void verifyTitleForTicket()
	{
		String title = ticketspage.getPageTitle();
		System.out.println("Page title is "+title);
	}
	@DataProvider
	public Object[][] getTickets()
	{
		Object data[][] = ExcelUtil.getData(Constants.TICKETS_SHEET);
		return data;
	}
	
	@Test (priority =3, dataProvider = "getTickets")
	public void createTicket(String ticketName)
	{
		ticketspage.createTicket(ticketName);
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
