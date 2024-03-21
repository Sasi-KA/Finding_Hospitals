package Utilities;

import java.io.IOException;

//import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testClasses.testClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;  //UI of the report
	public ExtentReports extent; //info into the report
	public ExtentTest test; //test results into the report

	
	public void onStart(ITestContext context) {
			
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/myReport.html");
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Finding Hospitals test report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
					
	}


	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.log(Status.PASS, result.getName() + " test case got passed successfully.");
		try {
			String path = testClass.screenShot();
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
		}
		
	}

	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, result.getName() + " test case got failed!!!!!!!!");
		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 
		test.log(Status.FAIL, result.getName() + " test case got passed successfully.");
		try {
			String path = testClass.screenShot();
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
		}
					
	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.SKIP,result.getName() + " test case got skipped!!!");
		
	}

	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
		
	
}
