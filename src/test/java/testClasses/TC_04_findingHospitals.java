package testClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ExcelUtils;

public class TC_04_findingHospitals extends testClass {
	
	@Test(priority = 7, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingPhoneFieldUsingInvalidMobileNumberFormat() throws IOException {
		logger.info("entering name and organisation in the demo form");
		regPOM.name(ExcelUtils.getData(1,0));
		regPOM.org(ExcelUtils.getData(1, 1));
		logger.info("verifying the phone field by giving invalid input format");
		Assert.assertEquals(regPOM.phn(ExcelUtils.getData(1, 2)), "rgba(159, 58, 56, 1)");
	}
	
	@Test(priority = 8, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingPhoneFieldUsingBVA() throws IOException {
		logger.info("using boundary value ananlysis to verify the phone field with different input values");
		Assert.assertEquals(regPOM.phn(ExcelUtils.getData(2, 2)), "rgba(159, 58, 56, 1)");
	}
	
	@Test(priority = 9, groups = {"regressionTest"}, dependsOnGroups = {"smokeTest"})
	void verifyingPhoneFieldUsingBVA_1() throws IOException {
		Assert.assertEquals(regPOM.phn(ExcelUtils.getData(3, 2)), "rgba(159, 58, 56, 1)");
	}

	
	
}
