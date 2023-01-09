package com.amazon.pages;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.amazon.utilities.*;


public class basePage {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
		// For Running Testcases in Headless Mode
		//ChromeOptions options = new ChromeOptions();
		//options.setHeadless(true);
		// Then pass options as a parameter to the new ChromeDriver() function
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
		extent = ExtentManager.getInstance("reports//Extent_demo.html");
	}
	@BeforeTest
	public void navigateToUrl()
	{
		driver.get(ReadingPropertiesFile.getProperty("url"));
	}
	@BeforeMethod
	public void startTest(Method method) {
		test = extent.startTest(method.getName());
	}
	@AfterMethod
	public void status(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS)
			test.log(LogStatus.PASS, "Test case got passed");
		else if (result.getStatus()==ITestResult.FAILURE) {
			ScreenShot.takeScreenShot(driver, result.getName());
			test.log(LogStatus.ERROR, result.getThrowable());
			test.log(LogStatus.FAIL, "Test case got failed");
		}
		extent.flush();
	}
	@AfterSuite
	public void tearDown() {
		// quitting the driver
		driver.quit();
	}

}
