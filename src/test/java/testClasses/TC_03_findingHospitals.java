package testClasses;

import org.testng.annotations.Test;

public class TC_03_findingHospitals extends testClass {

	@Test(priority = 5,groups = {"smokeTest"})
	void navigateToCorporate() {
		surgeriesPOM.scrollTop(js);
		surgeriesPOM.corporate();
		logger.info("navigating to the corporate");
	}
	
	@Test(priority = 6,groups = {"smokeTest"})
	void navigateToDemo() {
		logger.info("selecting heath and wellness");
		surgeriesPOM.healthNWellness(js);
		
	}
	
}
