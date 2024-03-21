package POM_files;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class doctors extends basepage {
	
	public doctors(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[text()='All Filters']") WebElement allFiltersDD;
	@FindBy(xpath = "//span[text()='Above ₹500']") WebElement priceElement;
	@FindBy(xpath = "(//span[text()='Available Today'])[1]") WebElement availabilityElement;
	
	public void allFilter() throws InterruptedException {
		allFiltersDD.click();
		Thread.sleep(1000);
		priceElement.click();
		Thread.sleep(1000);
		allFiltersDD.click();
		availabilityElement.click();
	}
	
	@FindBy(xpath = "//span[text()='Patient Stories']") WebElement patientStoriesDD;
	@FindBy(xpath = "//span[contains(text(),'10+ Patient')]") WebElement patientStoriesElement;
	
	public void patientStoriesFilter() {
		patientStoriesDD.click();
		patientStoriesElement.click();
	}
	
	@FindBy(xpath = "//span[text()='Experience']") WebElement expDD;
	@FindBy(xpath = "//span[contains(text(),'5+ Years')]") WebElement expElement;
	
	public void expFilter() {
		expDD.click();
		expElement.click();
	}
	
	@FindBy(xpath = "//span[text()='Relevance']") WebElement sortDD;
	@FindBy(xpath = "//span[text()='Experience - High to Low']") WebElement sortElement;
	
	public void sorting() {
		sortDD.click();
		sortElement.click();
	}
	
	@FindBy(className = "doctor-name") List<WebElement> doctorsNamesList;
	
	public String[] doctorsNames() {
		String[] names = new String[6];
		for(int i=0;i<6;i++) {
			names[i] = doctorsNamesList.get(i).getText();
		}
		return names;
	}
	
	@FindBy(xpath = "//*[@data-qa-id='doctor_experience']/parent::div/div/span") List<WebElement> doctorsFieldList;
	
	public String[] doctorsField() {
		String[] fields = new String[6];
		for(int i=0;i<6;i++) {
			fields[i] = doctorsFieldList.get(i).getText();
		}
		return fields;
	}
	
	@FindBy(xpath = "//*[@data-qa-id='doctor_experience']") List<WebElement> doctorsExpList;
	
	public String[] doctorsExp() {
		String[] exp = new String[6];
		for(int i=0;i<6;i++) {
			exp[i] = doctorsExpList.get(i).getText();
		}
		return exp;
	}
	
	@FindBy(xpath = "//*[@data-qa-id='practice_locality']/parent::a") List<WebElement> doctorsPracticeLocalityList;
	
	public String[] doctorsPracticeLoc() {
		String[] loc = new String[6];
		for(int i=0;i<6;i++) {
			loc[i] = doctorsPracticeLocalityList.get(i).getText();
		}
		return loc;
	}	
	
	@FindBy(xpath = "//*[text()='Surgeries']") WebElement surgeriesElement;
	
	public void surgeriesClick() {
		surgeriesElement.click();
	}
	
	
}
