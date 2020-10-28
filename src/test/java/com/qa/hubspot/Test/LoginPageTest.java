package com.qa.hubspot.Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.Pages.LoginPage;
import com.qa.hubspot.Util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic name: Login page")
@Feature("User story : design of login page")
public class LoginPageTest {
	WebDriver driver;
	BasePage basePage;
	LoginPage loginpage;
	Properties prop;

	@Parameters("browser")
	@BeforeTest
	public void setUp()
{
		basePage =  new BasePage();
		prop = basePage.init_prop();
	driver = basePage.init_driver(prop);
	loginpage= new LoginPage(driver);
	
	
}

@AfterTest
public void tearDown()
{
	driver.quit();
}
@Description("this is about login page")
@Severity(SeverityLevel.NORMAL)
@Test(priority=5)
public void verifyPageTitleForLoginPage()
{
	String title = loginpage.getPageTitle();
	Assert.assertEquals(title, Constants.LOGIN_TITLE);
}

@Severity(SeverityLevel.CRITICAL)
@Test(priority=1)
public void verifySignUpLinkForLoginPage()
{
 boolean val=	loginpage.isSignUpPresent();
 Assert.assertTrue(val);
}

@Severity(SeverityLevel.MINOR)
@Test(priority=2)
public void verifyShowPasswordForLoginPage()
{
 boolean val=	loginpage.isShowPasswordPresent();
 Assert.assertTrue(val);
}

@Severity(SeverityLevel.MINOR)
@Test(priority=3)
public void verifyForgotPasswordForLoginPage()
{
 boolean val=loginpage.isForgotPasswordPresent();
 Assert.assertTrue(val);
}

@Test(priority=4)
public void loginForLoginPage()
{
	loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
}

}
