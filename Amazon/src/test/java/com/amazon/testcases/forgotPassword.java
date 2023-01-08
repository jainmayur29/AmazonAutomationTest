package com.amazon.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.amazon.pages.basePage;
import com.amazon.utilities.ReadingPropertiesFile;

public class forgotPassword extends basePage {

	@Test(priority=1)
	public void forgetPasswordTest()
	{
		//driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(By.id("ap_email")).sendKeys(ReadingPropertiesFile.getProperty("username"));
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.linkText("Forgot Password")).click();
		driver.findElement(By.id("continue"));
	}
	
}
