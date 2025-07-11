package Testcases;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import Utilites.ConfigReader;

public class BaseClass {
public static ThreadLocal<WebDriver>tdriver=new ThreadLocal<WebDriver>();
public static  WebDriver driver;
public static  Logger log;
public static Properties properties;
SoftAssert SoftAssert=new SoftAssert();
private final static String LT_USERNAME ="kiranskylp";
private final static String LT_ACCESS_KEY ="LT_D8os1r1yryxKg4w77HfyV4ymxshOGvF4v7CD3jcYRc6cZC9";

//Methods
@BeforeClass(groups= {"sanity","regression","master","datadriven"})
@Parameters({"os","browser"})
public  void setup(String os ,String br) throws MalformedURLException
{	
ConfigReader configReader=new ConfigReader();
	properties=configReader.getProperties();
	log = LogManager.getLogger(this.getClass()); // Initialize logger
if (properties.getProperty("execution_env").equalsIgnoreCase("lambdatest"))
{
driver=(WebDriver) intializelambdaTest(os,br);
}
else if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
	DesiredCapabilities capabilities =new DesiredCapabilities();
	configurebrowsercapabilities(os,capabilities);
	configureoscapabilites(br,capabilities);
	driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
else
{
	driver=initializelocalbrowser(br);
}

// driver configuration
if(driver!=null)
{
setDriver(driver);
driver=getDriver();
driver.manage().deleteAllCookies();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
log.info("driver has started");
}
else
{
	log.error("driver is null");}
}

public static WebDriver getDriver() {
	// TODO Auto-generated method stub
	return tdriver.get();
}
// setting the webdriver instance to the  tdriver 
private static void setDriver(WebDriver driver) {
	tdriver.set(driver);
	
}

private static WebDriver initializelocalbrowser(String br) {
	switch(br.toLowerCase())
	{
	case "chrome":
		ChromeOptions chromeOptions=new ChromeOptions();
		   chromeOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                   "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
           chromeOptions.setAcceptInsecureCerts(true);
           return new ChromeDriver(chromeOptions);
	
           
	case "firefox":
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		 firefoxOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                 "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
         firefoxOptions.setAcceptInsecureCerts(true);
         firefoxOptions.addPreference("dom.webnotifications.enabled", false);
         firefoxOptions.addPreference("dom.disable_open_during_load", true);
         firefoxOptions.addPreference("extensions.showRecommendedInstalled", false);
         return new FirefoxDriver(firefoxOptions);

	case "edge":
		EdgeOptions edgeOptions = new EdgeOptions();
	    edgeOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
        edgeOptions.setAcceptInsecureCerts(true);
        return new EdgeDriver(edgeOptions);
		default:
			log.error("not a speicif browser");
			return null;
	}

}

private static void configureoscapabilites(String br, DesiredCapabilities capabilities) {
	switch (br.toLowerCase()) {
	case "chrome":
		capabilities.setBrowserName("chrome");
		break;
	case"edge":
		capabilities.setBrowserName("MicrosoftEgde");
		break;
	case "firefox":
		capabilities.setBrowserName("firefox");
		break;
		default:
			log.error("invalid browser"+br);
	}
}

private static void configurebrowsercapabilities(String os, DesiredCapabilities capabilities) {
	switch(os.toLowerCase())
	{
	case "window":
	capabilities.setPlatform(Platform.WIN11);
	break;
	case "linux":
		capabilities.setPlatform(Platform.LINUX);
		break;
	case "mac":
	capabilities.setPlatform(Platform.MAC);
	break;
	default:
		log.error("wrong platform");
	}
	
}

private static Object intializelambdaTest(String os, String br) {
	   DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("browserName", br);
       capabilities.setCapability("platformName", os);

       // LambdaTest-specific options
       HashMap<String, Object> ltOptions = new HashMap<>();
       ltOptions.put("username", LT_USERNAME);
       ltOptions.put("accessKey", LT_ACCESS_KEY);
       ltOptions.put("visual", true);
       ltOptions.put("video", true);
       ltOptions.put("build", "OpencartLambdaTestBuild");
       ltOptions.put("project", "OpencartLambdaTestProject");
       capabilities.setCapability("LT:Options", ltOptions);

       try {
           return new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities);
       } catch (Exception e) {
           log.error("Error initializing LambdaTest driver: " + e.getMessage());
           return null;
       }
}
@AfterClass(groups = {"sanity","regression","master","datadriven"})
public void teardown()
{
	   WebDriver driver=getDriver();  
	   driver.quit();
	   tdriver.remove();
	   log.info("The driver is removed");
	   
}
// utitlity methods


public String genereateString()
{
	return RandomStringUtils.randomAlphabetic(5);
	}
public String generateNumber()
{
	return RandomStringUtils.randomNumeric(5);
			}
	
	// generating alphanumerical value
public String generatingAlphaNumeric()
{
String generatedString=RandomStringUtils.randomAlphabetic(5);
String generatedNumber=RandomStringUtils.randomNumeric(5);
return generatedString+"@"+ generatedNumber;
}


}
