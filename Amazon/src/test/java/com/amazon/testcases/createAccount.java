package com.amazon.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.amazon.pages.*;

import com.amazon.pages.basePage;
import com.amazon.utilities.ReadingPropertiesFile;

public class createAccount extends basePage{
	
	@Test
	public void createAccountTest()
	{
		driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(createAccountPage.btn_createAccount).click();
		driver.findElement(By.id("ap_customer_name")).sendKeys(ReadingPropertiesFile.getProperty("yourName"));
		driver.findElement(By.id("ap_phone_number")).sendKeys(ReadingPropertiesFile.getProperty("mobileNumber"));
		driver.findElement(By.id("ap_email")).sendKeys(ReadingPropertiesFile.getProperty("emailAddress"));
		driver.findElement(By.id("ap_password")).sendKeys(ReadingPropertiesFile.getProperty("password"));
		//driver.findElement(By.id("continue")).click();
		System.out.println("Account created successfully");
		
	}

}
