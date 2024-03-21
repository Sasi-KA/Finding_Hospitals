package testClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ExcelUtils;

public class TC_01_findingHospitals extends testClass {
		
	@Test(priority = 0, groups = {"smokeTest"})
	void search() throws InterruptedException {
		
		logger.info("entering the search data");
		practoPOM.city("Bangalor");
		practoPOM.citySelect();
		practoPOM.type("cardio");
		logger.info("navigating to the doctors page");
	}
	
	@Test(priority = 1, groups = {"smokeTest"})
	void filter() throws InterruptedException {
		logger.info("applying the filters");
		practoPOM.typeSelect();
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
	
	@Test(priority = 2, groups = {"smokeTest"})
	void getDoctorsData() throws IOException {
		logger.info("getting the doctors details");
		if(doctorsPOM.doctorsNames() == null || doctorsPOM.doctorsField() == null || doctorsPOM.doctorsExp() == null || doctorsPOM.doctorsPracticeLoc() == null){
			logger.info("failed getting the doctors details");
			Assert.fail();
		}
		ExcelUtils.setData(doctorsPOM.doctorsNames(), doctorsPOM.doctorsField(), doctorsPOM.doctorsExp(), doctorsPOM.doctorsPracticeLoc());
		logger.info("printing the details in the excel");
	}  
}
