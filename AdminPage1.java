package com.ibm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage1 {
	// Following are the Xpath from the page
	// To give the login mail id
	@FindBy(xpath = "//input[@name='email']")
	WebElement Emailele;

	// To give the password
	@FindBy(xpath = "//input[@name='pword']")
	WebElement PasswordEle;

	// To click on the login button
	@FindBy(xpath = "//button[@class='btn btn-labeled btn-info m-b-5']")
	WebElement LoginButtonEle;

	// To click on the Catalog Tab
	@FindBy(xpath = "//a[@href='#']")
	WebElement CatalogTabEle;

	// To click on the 'Category' under 'Catalog'
	@FindBy(xpath = "//a[text()=' Categories']")
	WebElement CategoriesEle;

	// To click on the 'Add new' button
	@FindBy(xpath = "//a[@title='Add New']")
	WebElement AddButtonEle;

	// To add Category name
	@FindBy(xpath = "//input[@name='name']")
	WebElement CategoryNameELe;

	// To add Category tag title
	@FindBy(xpath = "//input[@name='tag_title']")
	WebElement TagTitleELe;

	// To add the sort
	@FindBy(xpath = "//input[@name='sort']")
	WebElement SortEle;

	// To change the status
	@FindBy(xpath = "//select[@name='status']/option[2]")
	WebElement StatusEle;
	
	//To go to the top of page
	@FindBy(xpath="//div[@id='toTop']")
	WebElement TopEle;
	
    //To save the content 
    @FindBy(xpath="//button[@title='Save']")
    WebElement SaveEle;

  //To click on Action for the added record
  	@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr[2]/td[6]/div/button")
  	WebElement ActionButtonEle;
  	
  	//To click on Delete button 
  	@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr[2]/td[6]/div/ul/li[2]")
  	WebElement DeleteEle;
  	
  	//To delete the record
  	@FindBy(xpath="//button[@class='confirm']")
  	WebElement DeleteRecordEle;
  
	WebDriverWait wait;
	WebDriver driver;

	// For using webdriver
	public AdminPage1(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	public void EnetrEmailAddress(String userName) {
		Emailele.sendKeys(userName);
	}

	public void EnetrPassword(String password) {
		PasswordEle.sendKeys(password);
	}

	public void ClickonLoginButton() {
		LoginButtonEle.click();
	}

	public void ClickonCatalogTabButton() {
		CatalogTabEle.click();
	}

	public void ClickonCategories() {
		CategoriesEle.click();
	}

	public void ClickonAddButton() {
		AddButtonEle.click();
	}

	public void EnterCategoryName(String name) {
		CategoryNameELe.sendKeys(name);
	}

	public void EnterTheTagTitle(String tag) {
		TagTitleELe.sendKeys(tag);
	}

	public void EnterTheSortOrder() {
		SortEle.sendKeys("1");
	}

	public void EntertheStatus()
	{
		StatusEle.click();
	}
	
	public void ClickToGoTop()
	{
		TopEle.click();
	}
	public void ClickonTheSaveButton()
	{
		SaveEle.click();
		
	}
	
	public void ClickonActionButton()
	{
		ActionButtonEle.click();
	}
	
	public void ClickonDelete()
	{
		DeleteEle.click();
	}

	public void ClickonDeleteRecord()
	{
		DeleteRecordEle.click();
	}

}
