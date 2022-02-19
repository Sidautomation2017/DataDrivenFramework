package com.edusol.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	static Logger log=LogManager.getLogger(Login_POM.class);

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
		log.info("User has enetered userid");
		password.sendKeys("admin123");
		log.info("User has enetered password");
		login_btn.click();
		String text=dashboardheader.getText();
		Assert.assertEquals(text, "Dashboard1");
		
		if(text.equalsIgnoreCase("Dashboard1")) {		
		
		test.log(Status.PASS, "Login sucess");
		//test.log(Status.PASS, "Login is Sucessfull"+test.addScreenCaptureFromPath(CommonMethods.getScreenshot(driver, "Test_Login")));
		log.info("Login is scuessfull");
		
	}
		else {
			test.log(Status.FAIL, "Login fail");
			//test.log(Status.FAIL, "Login is Fail  "+test.addScreenCaptureFromPath(CommonMethods.getScreenshot(driver, "Test_Login")));
			log.error("Login is failed");
			log.fatal("Login is complated blcoked");
		}

}
}