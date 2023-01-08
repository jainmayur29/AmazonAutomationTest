package com.amazon.testcases;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.LogStatus;

import com.amazon.pages.basePage;
import com.amazon.pages.homePage;
import com.amazon.pages.loginPage;
import com.amazon.pages.searchPage;
import com.amazon.pages.addToCartPage;
import com.amazon.utilities.ReadingPropertiesFile;

public class loginTest extends basePage {
	@Test(priority=1)
	public void login()
	{
		driver.findElement(homePage.btn_signIn).click();
		test.log(LogStatus.INFO, "Homepage sign in button got clicked");

		driver.findElement(loginPage.txt_username).sendKeys(ReadingPropertiesFile.getProperty("username"));
		test.log(LogStatus.INFO, "Username field is populated");
		driver.findElement(By.id("continue")).click();
		driver.findElement(loginPage.txt_password).sendKeys(ReadingPropertiesFile.getProperty("password"));
		test.log(LogStatus.INFO, "Password field is populated");

		driver.findElement(loginPage.btn_signIn).click();
		test.log(LogStatus.INFO, "Sin in button got clicked");
		
		test.log(LogStatus.INFO, "Asserting browser title");
		String ele = driver.findElement(By.id("nav-link-accountList")).getText();
		Assert.assertEquals("Hello, Mayur\n"+ "Account & Lists",ele);	
	}
	@Test(priority=2)
	public void addressVerifyTest() throws InterruptedException
	{
		String s=driver.findElement(By.id("nav-global-location-popover-link")).getText();
		System.out.println(s);
		test.log(LogStatus.INFO,"Validating the Address");
		Assert.assertEquals("Deliver to Jabalpur 482001", "Deliver to Jabalpur 482001");
				
	}
	@Test(priority=3)
	public void searchProductTest()
	{
	  test.log(LogStatus.INFO, " Enter the product name that you want to search");
	  driver.findElement(searchPage.btn_search).sendKeys(ReadingPropertiesFile.getProperty("productSearch"));
	
	  test.log(LogStatus.INFO, "Clicking on the search button");
	  driver.findElement(By.id("nav-search-submit-button")).click();
	  String ans= driver.findElement(By.xpath("//span[text()= 'Realme 9 Pro+ 5G (Aurora Green, 6GB RAM, 128GB Storage)']")).getText();
	  Assert.assertEquals("Realme 9 Pro+ 5G (Aurora Green, 6GB RAM, 128GB Storage)",ans);	
	}
	@Test(priority=4)
	public void addToCartTest() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[text()= 'Realme 9 Pro+ 5G (Aurora Green, 6GB RAM, 128GB Storage)']")).click();
		test.log(LogStatus.INFO," Clicking on add to cart button");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	    driver.findElement(By.id("add-to-cart-button")).click();
		test.log(LogStatus.INFO,"Add to cart button got clicked ");
		String ad= driver.findElement(By.xpath("//span[text()=' Added to Cart ']")).getText();
		System.out.println(ad);
		driver.findElement(By.id("attach-close_sideSheet-link")).click();
		Assert.assertEquals("",ad);
		Thread.sleep(5000);
	}
	@Test(priority=5)
		public void signOutTest()
		{
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.partialLinkText("Hello, Mayur"));
		action.moveToElement(element).build().perform();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Sign Out")));
		WebElement element2 = driver.findElement(By.partialLinkText("Sign Out"));
		action.moveToElement(element2);
		action.click().build().perform();
		
	}
}
