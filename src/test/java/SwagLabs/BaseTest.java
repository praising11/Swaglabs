package SwagLabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Utils.Emailutils;
import Utils.ExtendReport;

public class BaseTest {
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	
	
	@BeforeSuite
	public void setupReport() {
		extent = ExtendReport.getReportInstance();
	}
	
	@AfterSuite
	public void teardownReport() {
		extent.flush();
		String reportPath = ExtendReport.reportPath;
		Emailutils.sendTestReport(reportPath);
	}
	
	@BeforeMethod
	public void setUp() {
		
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://admin-demo.nopcommerce.com/login");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			
			String screenshotPath = ExtendReport.captureScreenshot(driver, "LoginFailure");
			test.fail("Test Failed.. Check Screenshot", 
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		
		
		if (driver != null) {
			
			driver.quit();
		}
	}

}
