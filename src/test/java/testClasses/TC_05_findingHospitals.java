package testClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ExcelUtils;

public class TC_05_findingHospitals extends testClass {

	@Test(priority = 10, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingMobileNumUsingValidInput() throws IOException {
		logger.info("verifying with the valid inputs");
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(1, 3)), "rgba(159, 58, 56, 1)");
	}
	
	@Test(priority = 11, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingMobileNumUsingValidInput_1() throws IOException {
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(2, 3)), "rgba(159, 58, 56, 1)");
	}
	
	@Test(priority = 12, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingMobileNumUsingValidInput_2() throws IOException {
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(3, 3)), "rgba(159, 58, 56, 1)");
	}
	
	@Test(priority = 13, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingMobileNumUsingValidInput_3() throws IOException {
		Assert.assertNotEquals(regPOM.phn(ExcelUtils.getData(4, 3)), "rgba(159, 58, 56, 1)");
	}
	
	
}
