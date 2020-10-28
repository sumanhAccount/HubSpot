
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
import com.qa.hubspot.Pages.HomePage;
import com.qa.hubspot.Pages.LoginPage;
import com.qa.hubspot.Util.Constants;
import com.qa.hubspot.Util.ExcelUtil;
import com.qa.hubspot.Pages.CompanyPage;

public class CompanyPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	
	CompanyPage companypage;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp()
{
	basePage =  new BasePage();
	prop = basePage.init_prop();
	driver = basePage.init_driver(prop);
	loginpage= new LoginPage(driver);
	homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	companypage = homepage.goToCompanies();
	
}	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	
	@Test(priority =1)
	public void verifycomapnyPageTitle()
	{
		String title = companypage.verifyHeader();
		System.out.println("Company page header is :"+title);
		Assert.assertEquals(title, "Comapnies");
	}
	
	@Test (priority =2)
	public void verifyPageTitleForCompany()
	{
		String title = companypage.getPageTitle();
		System.out.println("Company page title is :"+title);
		Assert.assertEquals(title, "Comapnies");
	}
	
	@DataProvider
	public Object[][] getcompany()
	{
		Object data[][] = ExcelUtil.getData(Constants.COMPANY_SHEET );
		return data;
	}
	
	@Test(priority =3, dataProvider = "getcompany")
	public void createDeals(String domName, String comName)
	{
		companypage.createCompany(domName, comName);
	}
}
