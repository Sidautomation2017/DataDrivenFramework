package com.edusol.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.edusol.base.CommonMethods;

public class Login_POM extends CommonMethods {
	WebDriver driver;

	// Login page Objects

	@FindBy(xpath = "//div[@class='head']/h1")
	WebElement dashboardheader;
	@FindBy(id = "txtUsername")
	WebElement login;
	@FindBy(id = "txtPassword")
	WebElement password;
	@FindBy(id = "btnLogin")
	WebElement login_btn;

	public Login_POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login(ExtentTest test) {
		test.log(Status.INFO, "Login test has started");			
		login.sendKeys("Admin");
		test.log(Status.PASS, "User Entered");
		password.sendKeys("admin123");
		login_btn.click();
		String text=dashboardheader.getText();
		if(text.equalsIgnoreCase("Dashboard")) {		
		
		test.log(Status.PASS, "Login is Sucessfull"+test.addScreenCaptureFromPath(CommonMethods.getScreenshot(driver, "Test_Login")));
		
	}
		else {
			test.log(Status.FAIL, "Login is Fail  "+test.addScreenCaptureFromPath(CommonMethods.getScreenshot(driver, "Test_Login")));
		}

}
}