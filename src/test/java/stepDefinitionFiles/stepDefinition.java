package stepDefinitionFiles;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import POM_files.doctors;
import POM_files.home;
import POM_files.registration;
import POM_files.surgeries;
import Utilities.ExcelUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {
	
	WebDriver driver;
	Properties properties;
	Logger logger;
	home practoPOM;
	doctors doctorsPOM;
	surgeries surgeriesPOM;
	registration regPOM;
	SoftAssert sa;
	JavascriptExecutor js;
	
	@Given("navigate to practo")
	public void navigate_to_practo() {
		driver = hooks.getDriver();
		properties = hooks.getProperties();
		logger = hooks.getLogger();
		driver.get("https://www.practo.com/");
		driver.manage().window().maximize();
		practoPOM = new home(driver);
		sa = new SoftAssert();
		//Assert.assertTrue(practoPOM.logo());
		
	}

	@Given("enter the search fields")
	public void enter_the_search_fields() throws InterruptedException {
		logger.info("entering the search data");
		practoPOM.city("Bangalor");
		practoPOM.citySelect();
		practoPOM.type("cardio");
		logger.info("navigating to the doctors page");
		//Assert.assertTrue(driver.getTitle().contains("Cardiologists"));
	}

	@When("apply all the filters")
	public void apply_all_the_filters() throws InterruptedException {
		logger.info("applying the filters");
		practoPOM.typeSelect();
		doctorsPOM = new doctors(driver);
		Thread.sleep(1000);
		doctorsPOM.patientStoriesFilter();
		Thread.sleep(1000);
		doctorsPOM.expFilter();
		Thread.sleep(1000);
		doctorsPOM.allFilter();
		Thread.sleep(1000);
		doctorsPOM.sorting();
		logger.info("applied all the filters");
	}

	@When("get the details of first five doctors")
	public void get_the_details_of_first_five_doctors() {
		logger.info("getting the doctors details");
		if(doctorsPOM.doctorsNames() == null || doctorsPOM.doctorsField() == null || doctorsPOM.doctorsExp() == null || doctorsPOM.doctorsPracticeLoc() == null){
			logger.info("failed getting the doctors details");
			Assert.fail();
		}
		ExcelUtils.setData(doctorsPOM.doctorsNames(), doctorsPOM.doctorsField(), doctorsPOM.doctorsExp(), doctorsPOM.doctorsPracticeLoc());
		logger.info("printing the details in the excel");
	}

	@When("navigate to surgeries")
	public void navigate_to_surgeries() {
		driver = hooks.getDriver();
		logger = hooks.getLogger();
		doctorsPOM = new doctors(driver);
		logger.info("navigating to the surgeries page");
		doctorsPOM.surgeriesClick();
	}

	@When("get all the surgeries list")
	public void get_all_the_surgeries_list() throws IOException {
		surgeriesPOM = new surgeries(driver);
		logger.info("scrolling down to the surgeries");
		js = (JavascriptExecutor)driver;
		surgeriesPOM.scrollDown(js);
		ExcelUtils.writeIntoExcel(surgeriesPOM.surgeriesList());
		logger.info("printing the surgeries list in the excel");
	}

	@When("navigate to form filling page")
	public void navigate_to_form_filling_page() {
		driver = hooks.getDriver();
		js = (JavascriptExecutor)driver;
		surgeriesPOM = new surgeries(driver);
		regPOM = new registration(driver);
		logger = hooks.getLogger();
		surgeriesPOM.scrollTop(js);
		surgeriesPOM.corporate();
		logger.info("navigating to the corporate");
		logger.info("selecting heath and wellness");
		surgeriesPOM.healthNWellness(js);
	}

	@When("fill the invalid details")
	public void fill_the_invalid_details() throws IOException {
		logger.info("entering name and organisation in the demo form");
		regPOM.name(ExcelUtils.getData(1,0));
		regPOM.org(ExcelUtils.getData(1, 1));
		logger.info("choosing the organization size and interested option");
		regPOM.orgSizeSelect();
		regPOM.interestedInSelect();
		logger.info("verifying the phone field by giving invalid input format");
		Assert.assertEquals(regPOM.phn(ExcelUtils.getData(1, 2)), "rgba(159, 58, 56, 1)");
		logger.info("using boundary value ananlysis to verify the phone field with different input values");
		Assert.assertEquals(regPOM.phn(ExcelUtils.getData(2, 2)), "rgba(159, 58, 56, 1)");
		Assert.assertEquals(regPOM.phn(ExcelUtils.getData(3, 2)), "rgba(159, 58, 56, 1)");
		logger.info("verifying the email field with the invalid inputs");
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(1, 4)), "rgba(159, 58, 56, 1)");
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(2, 4)), "rgba(159, 58, 56, 1)");
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(3, 4)), "rgba(159, 58, 56, 1)");
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(4, 4)), "rgba(159, 58, 56, 1)");
		
	}

	@Then("validate if Schedule a demo button is not disabled")
	public void validate_if_Schedule_a_demo_button_is_not_disabled() {
		logger.info("verifying the visibility of schedule a demo visibilty");
		Assert.assertFalse(regPOM.scheduleADemoVisibility());
	}

	@When("fill the valid details")
	public void fill_the_valid_details() throws IOException {
		logger.info("verifying with the valid inputs");
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(1, 3)), "rgba(159, 58, 56, 1)");
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(2, 3)), "rgba(159, 58, 56, 1)");
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(3, 3)), "rgba(159, 58, 56, 1)");
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(4, 3)), "rgba(159, 58, 56, 1)");
		logger.info("verifying the mail field with the valid inputs");
		Assert.assertNotEquals(regPOM.mail(ExcelUtils.getData(1, 5)), "rgba(159, 58, 56, 1)");
	}

	@Then("validate if Schedule a demo button is disabled")
	public void validate_if_schedule_a_demo_button_is_disabled() throws InterruptedException {
		logger.info("verifying the visibility of schedule a demo visibilty");
		Assert.assertTrue(regPOM.scheduleADemoVisibility());
		regPOM.scheduleADemoClick();
		Thread.sleep(20000);
		logger.info("getting the success message");
	}
	
	@Then("verify the Thankyou msg")
	public void verify_the_thankyou_msg() throws InterruptedException {
		logger.info("verifying the success message");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		String msg = regPOM.successMessage(wait);
		Assert.assertTrue(msg.equalsIgnoreCase("thank you"));
		
	}
	
}

