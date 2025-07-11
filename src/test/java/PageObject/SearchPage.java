package PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	WebDriver driver;
	WebDriverWait wait;

	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	
	@FindBy(xpath = "//div[@id='content']")
	WebElement SearchHeader;
	
	
	@FindBy(xpath = "//*[@id='content']/div[3]/image")
	List<WebElement> Products;
	
	
	public boolean isSearchPageExist()
	{
		try {
		String SearchText=wait.until(ExpectedConditions.visibilityOf(SearchHeader)).getText();
		return SearchText.contains("Search");
		}
		catch (Exception e2) {
			System.out.println("exception agaya"+ e2.getMessage());
		}
		return false;
	}
	 public boolean isProductExist(String productName) {
	        try {
	            wait.until(ExpectedConditions.visibilityOfAllElements(Products)); // Wait for product list to be visible
	            for (WebElement product : Products) {
	                if (product.getAttribute("title").equals(productName)) {
	                    return true;
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Error checking product existence: " + e.getMessage());
	        }
	        return false;
	    }
	    public ProductPage selectProduct(String productName) {
	        try {
	            wait.until(ExpectedConditions.visibilityOfAllElements(Products)); // Wait for product list to be visible
	            // Loop through products to find the matching product and click it
	            for (WebElement product : Products) {
	                if (product.getAttribute("title").equals(productName)) {
	                    wait.until(ExpectedConditions.elementToBeClickable(product)); // Wait for the product to be clickable
	                    product.click();
	                    return new ProductPage(driver);
	                }
	            }
	            System.out.println("Product not found: " + productName);
	        } catch (Exception e) {
	            System.out.println("Error selecting product: " + e.getMessage());
	        }
	        return null;
	
	
	    }	

}
