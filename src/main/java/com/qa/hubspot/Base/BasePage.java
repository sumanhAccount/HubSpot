package com.qa.hubspot.Base;

import java.io.File;
import java.io.FileInputStream;

import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.hubspot.Util.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
public WebDriver driver;
public Properties prop;
public OptionsManager optionmanager;
public static  ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); 

/** 
 * This method launches browser and initilise webdriver
 * @param bname
 * @return it returns WebDriver
 */
public WebDriver init_driver(Properties prop)
{
	String bname = prop.getProperty("browser");
	System.out.println("Browser name is "+bname);
	optionmanager = new OptionsManager(prop);
			
	if(bname.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		
		tlDriver.set(new ChromeDriver(optionmanager.getChromeOptions()));
	//driver= new ChromeDriver(optionmanager.getChromeOptions());
		}
	
		else if(bname.equals("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		
		tlDriver.set(new FirefoxDriver(optionmanager.getFirefoxOptions()));
		//driver= new FirefoxDriver(optionmanager.getFirefoxOptions());
		}
	
	else
	{
		System.out.println("please provide correct browser name");
	}
	getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	
	getDriver().get(prop.getProperty("url"));
	
	return getDriver();
}
	
	
	
public static synchronized WebDriver getDriver()
{
	return tlDriver.get();
}

public void init_remoreDriver(String browserName)
{
	if(browserName.equals("chrome"))
	{
		DesiredCapabilities cap=  DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY,optionmanager.getChromeOptions() );
		
		try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	if(browserName.equals("firefox"))
	{
		DesiredCapabilities cap=  DesiredCapabilities.firefox();
		cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS,optionmanager.getFirefoxOptions());
		
		try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public Properties init_prop()
{
	prop= new Properties();
	try {
		FileInputStream ip= new FileInputStream(".//src//main//java//com//qa//hubspot//config//config.properties");
		
		prop.load(ip);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return prop;
}

public String getScreenshot() {
	File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
	File destination = new File(path);
	try {
		FileUtils.copyFile(src, destination);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return path;
}
}
