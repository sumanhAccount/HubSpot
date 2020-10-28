package com.qa.hubspot.Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Pages.DealsPage;
import com.qa.hubspot.Pages.HomePage;
import com.qa.hubspot.Pages.LoginPage;
import com.qa.hubspot.Util.Constants;
import com.qa.hubspot.Util.ExcelUtil;

public class DealsPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	DealsPage dealspage;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp()
{
	basePage =  new BasePage();
	prop = basePage.init_prop();
	driver = basePage.init_driver(prop);
	loginpage= new LoginPage(driver);
	homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	dealspage = homepage.gotoDeals();
	
}	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	@DataProvider
	public Object[][] getDeals()
	{
		Object data[][] = ExcelUtil.getData(Constants.DEALS_SHEET );
		return data;
	}
	@Test(priority =2, dataProvider = "getDeals")
	public void createDeals()
	{
		dealspage.createDeals();
	}
	
	@Test(priority =1)
	public void verifyDealsPageTitle()
	{
		String title = dealspage.VerifyHeader();
		System.out.println("Deals page header is :"+title);
		Assert.assertEquals(title, "Deals");
	}
	
	
}
