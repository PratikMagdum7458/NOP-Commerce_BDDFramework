package StepDefination;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

import PageObject.AddCustomer;
import PageObject.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddCustomer ad;
	
	public Properties configProp;
	public Logger log;
	
	
	public static String generateEmail()
	{
	Faker faker=new Faker();
	return faker.internet().emailAddress();
	}
}
