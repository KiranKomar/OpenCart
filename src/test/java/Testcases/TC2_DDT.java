package Testcases;

import java.util.Properties;

import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.MyAccountPage;
import Utilites.DataProviders;

public class TC2_DDT extends BaseClass {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = { "Datadriven" })
    public void login(String email, String Password, String expectedResult) {
        String appurl = properties.getProperty("appURL");
        driver.get(appurl); // ✅ updated

        HomePage homepage = new HomePage(driver); // ✅ updated
        homepage.MyaccountClick();
        log.info("Clicked on 'My Account' link.");

        LoginPage loginPage = homepage.LoginPageClick();
        loginPage.emailAdd(email);
        loginPage.PasswordAdd(Password);
        loginPage.ClickLoin();

        MyAccountPage myAccountPage = new MyAccountPage(getDriver()); // ✅ updated
        boolean isOnMyAccountPage = myAccountPage.isMyAccountPageExists();

        if (expectedResult.equalsIgnoreCase("Valid")) {
            if (isOnMyAccountPage) {
                myAccountPage.clickLogout();
                SoftAssert.assertTrue(true, "Login success as expected");
            } else {
                SoftAssert.fail("Login failed despite valid credentials");
            }
        } else if (expectedResult.equalsIgnoreCase("Invalid")) {
            if (isOnMyAccountPage) {
                myAccountPage.clickLogout();
                SoftAssert.fail("Login succeeded with invalid credentials.");
            } else {
                SoftAssert.assertTrue(true, "Login failed as expected");
            }
        }
    }

    @Test(dependsOnMethods = "login")
    public void verifyAllCombinations() {
        SoftAssert.assertAll();
    }
}
