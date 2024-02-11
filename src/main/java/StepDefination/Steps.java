package StepDefination;

import java.io.IOException;

import io.cucumber.java.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import PageObject.AddCustomer;
import PageObject.LoginPage;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Steps extends BaseClass{

	@Before
	public void setUp() throws IOException
	{
		//Reading properties
		configProp=new Properties();
		FileInputStream fis=new FileInputStream("Config.properties");
		configProp.load(fis);
		
		String pr=configProp.getProperty("browser");
		if(pr.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Dr\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		log=log.getLogger("NOP-Commerce");
		PropertyConfigurator.configure("log4j.properties");
		
		log.info("Driver open");
		}
		else
		{
			log.info("Driver not found");
		}
	
	}
	
	//Login Steps
	
	@Given("User Launch chrome browser")
	public void user_launch_chrome_browser() {
		driver.manage().window().maximize();
		log.info("Window Page Maximize");
	}

	@When("User opens url {string}")
	public void user_opens_url(String url) throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);
		log.info("URL open");
			}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String em, String ps) throws InterruptedException {
		lp=new LoginPage(driver);
		lp.setEmail(em);
		log.info("Email Set");
		lp.setPassword(ps);
		log.info("Password Set");
		Thread.sleep(4000);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		 lp.clickLoginButton();
		 log.info("Click on Login Button");
		Thread.sleep(2000);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String ExpectedTitle) throws InterruptedException {
		if(driver.getPageSource().contains("Login was unsuccessful."))
		{
			driver.close();
			Assert.assertTrue(false);
			log.info("Login Failed...");
		}
		else
		{
			Assert.assertEquals(ExpectedTitle, driver.getTitle());
			log.info("Successfully Login...");
		}
		Thread.sleep(2000);
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		lp.clickLogout();
		log.info("Click on logout Button");
		Thread.sleep(2000);
	}

	@Then("close browser")
	public void close_browser() {
		driver.quit();
		log.info("Browser Closed");
	}
	
	//*************************************************************************************
	//Add Customer Steps
	
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	 ad=new AddCustomer(driver);
	 ad.clickOnCustomersMenu();
	 log.info("Click on clickOnCustomersMenu");
	 Thread.sleep(2000);
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		 ad.clickOnCustomersMenuItem();
		 log.info("Click on clickOnCustomersMenuItem");
	    Thread.sleep(2000);
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
	  ad.clickOnAddnew();
	  log.info("Click on clickOnAddnew");
	  Thread.sleep(2000);
	}

	
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	  Assert.assertEquals("Add a new customer / nopCommerce administration", ad.getPageTitle());
	  
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		ad.enterEmail(generateEmail());
		log.info("Email Entered.......");
		ad.enterPassword("test1");
		log.info("Password Entered.......");
		ad.enterFirstName("Pratik");
		log.info("FirstName Entered.......");
		ad.enterLastName("Magadum");
		log.info("LastName Entered.......");
		ad.enterGender("Male");
		log.info("Gender Entered.......");
		ad.enterDob("12/10/1998");
		log.info("Date Entered.......");
		ad.enterCompanyName("TNT Technologies");
		log.info("Company Name Entered.......");
		ad.enterAdminContent("Admin content");
		log.info("Admin Content Entered.......");
		ad.enterManagerOfVendor("Vendor 1");
		log.info("Manager Vendor Entered.......");
		 Thread.sleep(3000);
	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
	    ad.clickOnSave();
	    log.info("Click on save Button");
	    Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
	  
		String bodyTagText = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']")).getText();
		if(bodyTagText.contains(exptectedConfirmationMsg))
		{
			Assert.assertTrue(true);//pass
			log.info("Customer Save Successfully");
		}
		else
		{
			log.info("Customer Not Save");
			Assert.assertTrue(false);//fail
			
		}
	}
	
}
