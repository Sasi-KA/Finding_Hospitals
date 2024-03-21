package POM_files;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class home extends basepage {
	
	Actions action;
	
	public home(WebDriver driver) {
		super(driver);
		action = new Actions(driver);
		
	}
	
	@FindBy(linkText = "https://www.practo.com/") WebElement logoElement;
	
	public boolean logo() {
		return logoElement.isDisplayed();
	}
		
	@FindBy(xpath = "(//input)[1]") WebElement cityElement;
	 
	public void city(String loc) {
		cityElement.clear();
		cityElement.sendKeys(loc);
	}
	
	@FindBy(xpath = "//*[text()='Bangalore']") WebElement bangloreElement;
	
	public void citySelect() {
		try {
			bangloreElement.click();
		} catch (StaleElementReferenceException e) {
			action.sendKeys(Keys.BACK_SPACE).perform();
			action.sendKeys("r").perform();
			bangloreElement.click();
		}
	}
	
	@FindBy(xpath = "(//input)[2]") WebElement typeElement;
	
	public void type(String type) {
		typeElement.clear();
		typeElement.sendKeys(type);
	}
	
	@FindBy(xpath = "//*[text()='Cardiologist']") WebElement dentistElement;
	
	public void typeSelect() {
		dentistElement.click();
	}
	
}
