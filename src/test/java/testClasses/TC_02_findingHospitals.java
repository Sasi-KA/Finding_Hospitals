package testClasses;

import java.io.IOException;

import org.testng.annotations.Test;

import Utilities.ExcelUtils;

public class TC_02_findingHospitals extends testClass {
	
	@Test(priority = 3,groups = {"smokeTest"})
	void navigateToSurgeries() {
		logger.info("navigating to the surgeries page");
		surgeriesPOM.surgeriesClick();
		
	}
	
	@Test(priority = 4,groups = {"smokeTest"})
	void getSurgeriesList() throws IOException {
		logger.info("scrolling down to the surgeries");
		surgeriesPOM.scrollDown(js);
		ExcelUtils.writeIntoExcel(surgeriesPOM.surgeriesList());
		logger.info("printing the surgeries list in the excel");
		
	}
	
}
