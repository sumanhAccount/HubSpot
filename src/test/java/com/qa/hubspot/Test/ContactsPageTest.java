package com.qa.hubspot.Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Pages.ContactsPage;
import com.qa.hubspot.Pages.HomePage;
import com.qa.hubspot.Pages.LoginPage;
import com.qa.hubspot.Util.Constants;
import com.qa.hubspot.Util.ExcelUtil;

public class ContactsPageTest {
	WebDriver driver;
	BasePage basePage;
	LoginPage loginpage;
	Properties prop;
	HomePage homepage;
	ContactsPage contactpage;

	
	@BeforeTest
	public void setUp()
{
	basePage =  new BasePage();
	prop = basePage.init_prop();
	driver = basePage.init_driver(prop);
	loginpage= new LoginPage(driver);
	homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	contactpage = homepage.gotoPrimaryContact();
}
	
	@Test (priority =1)
	public void verifyContactPageTitleTest()
	{
		String tit = contactpage.verifyContactPageTitle();
		System.out.println(tit);
		Assert.assertEquals(tit, "Contacts", "Not matching");
	}

	@Test(priority =2)
	public void verifyContactPageHeader()
	{
		String tit = contactpage.verifyContactHeader();
		System.out.println(tit);
		Assert.assertEquals(tit, Constants.CONTACT_PAGE_HEADER,"Contact Header not matching");
	}
	
	@DataProvider
	public Object[][] getContactsData()
	{
		Object data[][] = ExcelUtil.getData(Constants.CONTACTS_SHEET);
		return data;
	}
	
	@Test (priority = 3, dataProvider = "getContactsData")
	public void verifyCreateContact(String emailid, String firstname, String lastname, String jobtitle)
	{
				
		contactpage.createContact(emailid, firstname, lastname, jobtitle);

	}
	
//	@Test(priority =4)
//	public void verifyCreatedContact( )
//	{
//		String contact = contactpage.contactCreated();
//		System.out.println(contact);
//		Assert.assertEquals(contact, "Tom12 Dic12", "Correct matching not found");
//	}
	
@AfterTest
public void tearDown()
{
	driver.quit();
}



}
