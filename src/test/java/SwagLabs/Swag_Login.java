package SwagLabs;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.Emailutils;
import Utils.ExtendReport;

public class Swag_Login {

	WebDriver driver;
	public long maximumwait = 30;
	public  static String browsername=null;
	
	protected static ExtentReports extent;
	protected ExtentTest test;
	
	public Logger log=LogManager.getLogger(Swag_Login.class);

	String[][] logincredentials = { { "standard_user", "secret_sauce" }

	};

	@DataProvider(name = "loginvalue")
	public String[][] login() {
		return logincredentials;

	}

	@Test
	public void browsersetting() {

		try {
			extent=ExtendReport.getReportInstance();
			PropertiesFile.getprop();
			if (browsername.equalsIgnoreCase("chrome")) {
				driver=new ChromeDriver();
				
			}else if(browsername.equalsIgnoreCase("firefox"))
				driver=new FirefoxDriver();
			
			else if(browsername.equalsIgnoreCase("edge"))
				driver=new EdgeDriver();
			
			
			//driver = new ChromeDriver();
			driver.manage().window().maximize();

			driver.get("https://www.saucedemo.com/v1/");
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(maximumwait));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maximumwait));

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maximumwait));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

			String expectedUrl = "https://www.saucedemo.com/v1/";
			if (driver.getCurrentUrl().equals(expectedUrl)) {
				System.out.println("URL Validation Passed");
				log.info("url validation passed");
			} else {
				log.error("Url is not matched");
				System.out.println("URL Validation Failed: " + driver.getCurrentUrl());
			}

			String expectedTitle = "Swag Labs";
			if (driver.getTitle().equals(expectedTitle)) {
				System.out.println("Page Title Validation Passed");
			} else {
				System.out.println("Page Title Validation Failed: " + driver.getTitle());
			}

			if (driver.findElement(By.id("login-button")).isDisplayed()) {
				System.out.println("Login Page Loaded Successfully");
			} else {
				System.out.println("Login Page Not Loaded Properly");
			}

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}

	@Test(dataProvider = "loginvalue", priority = 0)
	public void login(String user, String pass) {

		WebElement username, password, click;

		username = driver.findElement(By.id("user-name"));

		username.sendKeys(user);
		password = driver.findElement(By.id("password"));
		password.sendKeys(pass);

		click = driver.findElement(By.id("login-button"));
		click.click();

	}
	
	
	/*
	 * @AfterSuite public void quit() { if(driver!=null) { driver.quit(); String
	 * reportpath=ExtendReport.reportPath; Emailutils.sendTestReport(reportpath); }
	 * }
	 */
	 
}
