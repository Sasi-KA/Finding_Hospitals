package testClasses;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import POM_files.doctors;
import POM_files.home;
import POM_files.registration;
import POM_files.surgeries;
import factory.BaseClass;

public class testClass {

	public static WebDriver driver;
	public static Properties properties;
	public static Logger logger;

	public static home practoPOM;
	public static doctors doctorsPOM;
	public static surgeries surgeriesPOM;
	public static registration regPOM;
	static JavascriptExecutor js;
	

  @BeforeTest(groups = {"smokeTest"})
  @Parameters({"browser"})
  public void setUp(String browser) throws IOException {
	  if(browser.equalsIgnoreCase("chrome")) {
		  driver = new ChromeDriver();
		  logger = LogManager.getLogger();
		  System.out.println("running through chrome");
	  }
	  else if(browser.equalsIgnoreCase("edge")) {
		  driver = new EdgeDriver();
		  logger = LogManager.getLogger();
		  System.out.println("running through edge");
	  }
	  else if(browser.equalsIgnoreCase("user_input")) {
		  BaseClass.initilizeBrowser();
		  driver = BaseClass.getDriver();
		  logger = BaseClass.getLogger1();
	  }
		driver.get(BaseClass.getProperties().getProperty("appUrl"));
		driver.manage().window().maximize();
		practoPOM = new home(driver);
		doctorsPOM = new doctors(driver);
		surgeriesPOM = new surgeries(driver);
		regPOM = new registration(driver);
		js = (JavascriptExecutor)driver;
	
  }
  static int i=1;
  public static String screenShot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\ScreenShots\\"+i+"ss.png";
	    File trgFile = new File(path);
	    i++;
	    FileUtils.copyFile(src, trgFile);
	    return path;
	}
  public static void openExtentReport() throws IOException {
      String extentReportFilePath = System.getProperty("user.dir")+ "//reports//myReport.html";

          File reportFile = new File(extentReportFilePath);
          if (reportFile.exists()) {
              Desktop.getDesktop().browse(reportFile.toURI());
          } else {
              System.out.println("ExtentReports file not found at: " + extentReportFilePath);
          }
  }
  
  @AfterTest(groups = "regressionTest")
  public void afterClass() throws IOException {
	  driver.quit();
	  openExtentReport();
  }

}
