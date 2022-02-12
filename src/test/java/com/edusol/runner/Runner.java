package com.edusol.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.edusol.base.BrowserFactory;
import com.edusol.base.CommonMethods;
import com.edusol.base.Constants;
import com.edusol.pom.Login_POM;
import com.edusol.pom.Logout_POM;
import com.edusol.utilities.ExtentReportGenerator;
import com.edusol.utilities.PropertyFileReader;

public class Runner {

	WebDriver driver;
	Login_POM loginPage;
	Logout_POM logoutPage;
	public static ExtentTest test;
	 
	@BeforeSuite
	public void initialSetup() {
		
	}

	@BeforeMethod
	public void setup() {
		driver = BrowserFactory.browserLaunch(PropertyFileReader.readProperties(Constants.BROWSER),
				PropertyFileReader.readProperties(Constants.URL));
	}

	@Test
	public void Login() {		
		test=ExtentReportGenerator.startTest("Login");		
		loginPage = new Login_POM(driver);
		loginPage.login(test);			

	}

	@Test(enabled = true)
	public void LoginLogout() {
		test=ExtentReportGenerator.startTest("LoginLogout");		
		loginPage = new Login_POM(driver);
		loginPage.login(test);
		logoutPage = new Logout_POM(driver);
		logoutPage.logout(test);
		

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();		
	}

	@AfterSuite
	public void afterSuite() {
		ExtentReportGenerator.createExtentReport().flush();
	}
}
