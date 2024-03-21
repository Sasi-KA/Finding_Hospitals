package testClasses;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_07_findingHospitals extends testClass {

	@Test(priority = 20, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyScheduleADemoButtonVisibility() throws IOException, InterruptedException {
		logger.info("choosing the organization size and interested option");
		regPOM.orgSizeSelect();
		regPOM.interestedInSelect();
		SoftAssert sa = new SoftAssert();
		logger.info("verifying the visibility of schedule a demo visibilty");
		sa.assertTrue(regPOM.scheduleADemoVisibility());
		regPOM.scheduleADemoClick();
		Thread.sleep(20000);
		logger.info("getting the success message");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		System.out.println(regPOM.successMessage(wait));
		sa.assertAll();
	}
	
}
