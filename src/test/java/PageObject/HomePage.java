package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		 this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	//Myaccount 
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement LinkAccount; 
	//Register
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement LinkRegister; 
    //Login
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement LinkLogin; 
	
@FindBy(xpath = "//input[@placeholder='Search']")
WebElement FieldSearch;

@FindBy(xpath = "//button[@class='btn btn-light btn-lg']")
WebElement BtnSearch;
	
	public boolean isHomeExist()
	{
		try
		{
			return driver.getTitle().equals("Your Store");
		}
		catch (Exception e) {
			return false;
		}
		
	}
	public void MyaccountClick()
	{
		try {
		
		wait.until(ExpectedConditions.elementToBeClickable(LinkAccount)).click();
			
		} catch (Exception e) {
			System.out.println("got exection while clicking"+e.getMessage());
		}
	}
	public RegistrationPage RegisterClick()
	{
		try {
			wait.until(ExpectedConditions.elementToBeClickable(LinkRegister)).click();
			return new RegistrationPage(driver);
			
		} catch (Exception e) {
			System.out.println("Exception while clicking register"+e.getMessage());
			return null;
		}
	}
		
		public LoginPage LoginPageClick()
		{
			try {
				wait.until(ExpectedConditions.elementToBeClickable(LinkLogin)).click();
				return new LoginPage(driver);
			} catch (Exception e) {
				System.out.println("exection while click login"+e.getMessage());
				return null;
			}
		}
		
		public void SearchFeildSend(String SearchKey)
		{
			try {
				FieldSearch.clear();
				wait.until(ExpectedConditions.visibilityOf(FieldSearch)).sendKeys(SearchKey);
				
			} catch (Exception e) {
				System.out.println("exection while sending key login"+e.getMessage());
			}
		}
		public SearchPage Searching()
		{
			try {
				wait.until(ExpectedConditions.elementToBeClickable(BtnSearch)).click();
				return new SearchPage(driver);
			} catch (Exception e) {
				System.out.println("exection while click searching"+e.getMessage());
				return null;
			}
		}
		
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

