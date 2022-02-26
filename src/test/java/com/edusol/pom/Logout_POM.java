package com.edusol.pom;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.edusol.base.CommonMethods;

public class Logout_POM extends CommonMethods{
	
	WebDriver driver;
	// Objects

	@FindBy(id = "welcome")
	WebElement welcome_btn;
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logout_btn;
	@FindBy(id = "logInPanelHeading")
	WebElement loginpage_heading;

	
	// Methods
	
	public Logout_POM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void logout(ExtentTest test, Map<String, String> data)  {
		if(data.containsKey("Logout_required") && data.get("Logout_required").equalsIgnoreCase("yes")) {
		
		welcome_btn.click();
	test.log(Status.INFO, "Logout in in porgres");
		CommonMethods.jsClick(driver, logout_btn);		
		
		CommonMethods.softassert.assertTrue(loginpage_heading.isDisplayed());
		//Assert.assertTrue(loginpage_heading.isDisplayed());  //hard assertions
		System.out.println(" loginLogout test executed ");
		test.log(Status.PASS, "LoginLogout is Sucessfull"+test.addScreenCaptureFromPath(CommonMethods.getScreenshot(driver, "Test1")));
	
		
	}
	}
}
