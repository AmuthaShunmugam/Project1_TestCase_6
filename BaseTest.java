package com.ibm.test;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.pages.AdminPage;
import com.ibm.pages.AdminPage1;
import com.ibm.pages.UserPage;
import com.ibm.utilities.ExcelUtil;
import com.ibm.utilities.PropertiesFileHandler;

public class BaseTest {
	WebDriver driver;
	WebDriverWait wait;
	PropertiesFileHandler propFIleHandler;
	HashMap<String, String> data;
	@BeforeSuite
	public void propertiesfile() throws IOException
	{
		 String file = "./TestData/data.properties";
		 	PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		 	data = propFileHandler.getPropertiesAsMap(file); 
	}
	@BeforeMethod
	public void BrowserInitialization()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	 	driver = new ChromeDriver();
	 	wait = new WebDriverWait(driver, 60);
	 	driver.manage().window().maximize();
	 	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}
	@Test
	public void DeleteAndCheckProductInUserPage() throws InterruptedException
	{
		String url = data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
		String name=data.get("Category");
		String tag=data.get("Tagtitle");
		String Error1=data.get("ErrorMessage");
		driver.get(url);
		//To Add a record in admin portal and then check it in user portal whether it is added or not
		System.out.println("To Add a record in admin portal and then check it in user portal whether it is added or not");
		AdminPage1 home = new AdminPage1(driver, wait);
		home.EnetrEmailAddress(userName);
		home.EnetrPassword(password);
		home.ClickonLoginButton();
		home.ClickonCatalogTabButton();
		home.ClickonCategories();
		home.ClickonAddButton();
		home.EnterCategoryName(name);
		home.EnterTheTagTitle(tag);
		home.EnterTheSortOrder();
		home.EntertheStatus();
		home.ClickToGoTop();Thread.sleep(2000);
		home.ClickonTheSaveButton();
        driver.navigate().to("https://atozgroceries.com");
		UserPage user=new UserPage(driver,wait);
		user.ClickonShopByCategory(); Thread.sleep(2000);
		WebElement UData=driver.findElement(By.xpath("//*[@class='category-drop-list category-drop-list-show']/div/ul/li[2]"));
		String UserData=UData.getText();
		System.out.println("The data added is:" +UserData);
		System.out.println("The data from properties file:" +name);
		Assert.assertEquals(name, UserData);
		System.out.println("The category name added in admin portal is present in the user portal");
		System.out.println(".................................................................");
		//Once record added is checked going into admin portal and delete the record
		System.out.println("To Delete the record in admin Portal");
		driver.get(url);
		home.EnetrEmailAddress(userName);
		home.EnetrPassword(password);
		home.ClickonLoginButton();
		home.ClickonCatalogTabButton();
		home.ClickonCategories();
		home.ClickonActionButton();
		home.ClickonDelete();Thread.sleep(2000);
		home.ClickonDeleteRecord();Thread.sleep(2000);
		System.out.println("The record added is deleted");
		driver.navigate().back();
		WebElement Error=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
		String ErrorMsg= Error.getText();
		System.out.println("Message of data deleted: " +ErrorMsg);
		System.out.println("....................................................................");
		//Navigate to user portal to check whether it is deleted
		driver.navigate().to("https://atozgroceries.com");
		UserPage user1=new UserPage(driver,wait);
		user1.ClickonShopByCategory(); Thread.sleep(2000);
		WebElement UData1=driver.findElement(By.xpath("//*[@class='category-drop-list category-drop-list-show']/div/ul/li[2]"));
		String UserData1=UData1.getText();
		System.out.println("The data is:" +UserData1);
		System.out.println("The data from property file:" +name);
		Assert.assertNotSame(name, UserData);
		System.out.println("The data is not present in the user portal");
		}
}
