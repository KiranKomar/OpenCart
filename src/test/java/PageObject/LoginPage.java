package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
@FindBy(xpath = "//input[@id='input-email']")
WebElement EmailField;

@FindBy(xpath = "//input[@id='input-password']")
WebElement PasswordField;

@FindBy(xpath = "//button[normalize-space()='Login']")
WebElement LoginButton;

	//input[@id='input-email']
	//input[@id='input-password']
	public void emailAdd(String email)
	{
		try {
			EmailField.clear();
			wait.until(ExpectedConditions.visibilityOf(EmailField)).sendKeys(email);
		} catch (Exception e) {
			System.out.println("exeption on emailfield"+e.getMessage());
		}
	}
	public void PasswordAdd(String Password)
	{
		try {
			PasswordField.clear();
			wait.until(ExpectedConditions.visibilityOf(PasswordField)).sendKeys(Password);
		} catch (Exception e) {
			System.out.println("exeption on Password"+e.getMessage());
		}
	}
	public LoggedInHome ClickLoin( )
	{
		try {
			
			wait.until(ExpectedConditions.elementToBeClickable(LoginButton)).click();
			return new LoggedInHome(driver);
		} catch (Exception e) {
			System.out.println("exeption on loginButton Clicking"+e.getMessage());
			return null;
		}
	}
	

}
