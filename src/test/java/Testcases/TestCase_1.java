package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.HomePage;

import PageObject.RegistrationPage;

public class TestCase_1 extends BaseClass {
/*	 * Test Case: Account Registration
	 * 
	 * Steps:
	 * 1) Navigate to application URL
	 * 2) Navigate to 'My Account' and click 'Register'
	 * 3) Fill in registration details
	 * 4) Agree to Privacy Policy and submit registration
	 * 5) Validate confirmation message
	 */
@Test(groups= {"regression","master"})
public void TestUserRegistration()
{
String appURL=properties.getProperty("appURL")	;
driver.get(appURL);
log.info("App is logging into the application"+ appURL);


HomePage homepage=new HomePage(driver);
homepage.MyaccountClick();
log.info("Clicked on 'My Account' link.");
RegistrationPage Registrationpage =homepage.RegisterClick();

log.info("Clicked on registration page");
// filling the registration field details

Registrationpage.setFirstName(genereateString().toUpperCase());
log.info("entered the firstname");

Registrationpage.setLastName(genereateString().toUpperCase());
log.info("entered the last name");

String email=genereateString()+"@gmail.com";
Registrationpage.setEmail(email);
log.info("entered the email");

Registrationpage.setTelephone(generateNumber());
log.info("entered the mobilenumber");

String password=generatingAlphaNumeric();
Registrationpage.setPassword(password);
log.info("entered the password");

Registrationpage.setConfirmPassword(password);
log.info("confirmed password");

Registrationpage.setPrivacyPolicy();
log.info("privacy policy clicked");

Registrationpage.clickContinue();
log.info("continue button clicked");

// validation the confirmation message
String ConfirmationMessae=Registrationpage.getConfirmationMsg();
Assert.assertEquals(ConfirmationMessae, "Your Account Has Been Created!...","not matching");
log.info("completed the assertions");
}

}
