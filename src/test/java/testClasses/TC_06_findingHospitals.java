package testClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ExcelUtils;

public class TC_06_findingHospitals extends testClass {

	@Test(priority = 14, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingEmailUsingInvalidInput() throws IOException {
		logger.info("verifying the email field with the invalid inputs");
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(1, 4)), "rgba(159, 58, 56, 1)");
	}
	
	@Test(priority = 15, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingEmailUsingInvalidInput_1() throws IOException {
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(2, 4)), "rgba(159, 58, 56, 1)");
	}
	@Test(priority = 16, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingEmailUsingInvalidInput_2() throws IOException {
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(3, 4)), "rgba(159, 58, 56, 1)");
	}
	@Test(priority = 17, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingEmailUsingInvalidInput_3() throws IOException {
		Assert.assertEquals(regPOM.mail(ExcelUtils.getData(4, 4)), "rgba(159, 58, 56, 1)");
	}
	
	@Test(priority = 18, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingEmailUsingValidInput() throws IOException {
		logger.info("verifying the mail field with the valid inputs");
		Assert.assertNotEquals(regPOM.mail(ExcelUtils.getData(1, 5)), "rgba(159, 58, 56, 1)");
	}
	
}
