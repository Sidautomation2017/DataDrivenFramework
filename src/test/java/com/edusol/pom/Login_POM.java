package com.edusol.pom;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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

	public void login(ExtentTest test, Map<String, String> map) {
		test.log(Status.INFO, "Login test has started");			
		login.sendKeys(map.get("UserID"));
		test.log(Status.PASS, "User Entered");
		log.info("User has enetered userid");
		password.sendKeys(map.get("Password"));
		log.info("User has enetered password");
		login_btn.click();
		String text=dashboardheader.getText();
		//Assert.assertEquals(text, "Dashboard2");		
		CommonMethods.softassert.assertEquals(text, "Dashboard2");
		
		}
}