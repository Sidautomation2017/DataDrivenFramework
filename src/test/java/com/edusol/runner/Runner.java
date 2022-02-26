package com.edusol.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.edusol.base.BrowserFactory;
import com.edusol.base.CommonMethods;
import com.edusol.base.Constants;
import com.edusol.pom.Login_POM;
import com.edusol.pom.Logout_POM;
import com.edusol.pom.Recruitment_POM;
import com.edusol.utilities.ExcelReader;
import com.edusol.utilities.ExtentReportGenerator;
import com.edusol.utilities.PropertyFileReader;

public class Runner {

	public static WebDriver driver;
	Login_POM loginPage;
	Logout_POM logoutPage;
	Recruitment_POM recruitment;
	public static ExtentTest test;
	ExcelReader reader;
	 
	@BeforeSuite
	public void initialSetup() {
		
		reader=new ExcelReader();
		
	}

	@BeforeMethod
	public void setup() {
		
		  driver =
		  BrowserFactory.browserLaunch(PropertyFileReader.readProperties(Constants.
		  BROWSER), PropertyFileReader.readProperties(Constants.URL));
		 
	}
	
	
	@DataProvider (name="dp")
	public Object[][] dataSupplier() throws IOException{	
		
				  
		  int lastRowNum = reader.getRowCount();	
		  
		  Object[][] obj = new Object[lastRowNum-1][1];
		  
		  for (int i = 1; i < lastRowNum; i++) { 
			  Map<String,String> datamap = new  HashMap<>(); 			
		 datamap=reader.getExcelData(i);			
			obj[i-1][0] = datamap;	 // [0][0]= tc1    , [1][0]=tc2, 
		
	  }
		  return obj;
	}

 
	@Test (dataProvider = "dp")
	public void testOrageHRM(Map<String, String> data) throws InterruptedException {		
		
		
		  test=ExtentReportGenerator.startTest(data.get("TestCaseObjective")); 
		  loginPage = new Login_POM(driver); 
		  loginPage.login(test, data); 
		  recruitment=new  Recruitment_POM(driver);
		  recruitment.enterCandiadateDetails(test, data);
		  logoutPage=new Logout_POM(driver);
		  logoutPage.logout(test, data); 
		  CommonMethods.softassert.assertAll("Observed Failure");
		
	}

	
	

	@AfterMethod
	public void tearDown() {
		driver.quit();	
		
	}

	@AfterTest()
	
	public void afterTest()
	{
		
	}
	
	@AfterSuite
	public void afterSuite() {

		ExtentReportGenerator.createExtentReport().flush();
	}
}
