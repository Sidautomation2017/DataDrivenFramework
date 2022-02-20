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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
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
	 
	@BeforeSuite
	public void initialSetup() {
		
		System.out.println("test");
		
	}

	@BeforeMethod
	public void setup() {
		
		  driver =
		  BrowserFactory.browserLaunch(PropertyFileReader.readProperties(Constants.
		  BROWSER), PropertyFileReader.readProperties(Constants.URL));
		 
	}
	
	@DataProvider (name="dp")
	public Object[][] dataSupplier() throws IOException{	
		
		   File file = new
		  File("D:\\Automation Testing\\workspace\\DataDrivenFramework\\src\\test\\resources\\Data\\TestData.xlsx"
		  ); FileInputStream fis = new FileInputStream(file);
		  
		  XSSFWorkbook wb = new XSSFWorkbook(fis); XSSFSheet sheet = wb.getSheetAt(0);
		  wb.close(); int lastRowNum = sheet.getLastRowNum() ; int lastCellNum =
		  sheet.getRow(0).getLastCellNum(); Object[][] obj = new Object[lastRowNum][1];
		  
		  for (int i = 0; i < lastRowNum; i++) { Map<Object, Object> datamap = new
		  HashMap<>(); for (int j = 0; j < lastCellNum; j++) {
		  datamap.put(sheet.getRow(0).getCell(j).toString(),
		  sheet.getRow(i+1).getCell(j).toString()); } obj[i][0] = datamap;
		  
		  } return obj;
		 
		
	  }
				


	@Test (dataProvider = "dp")
	public void Login(Map<String, String> map) throws InterruptedException {		
		
		
		  test=ExtentReportGenerator.startTest("Login"); 
		  loginPage = new Login_POM(driver); 
		  loginPage.login(test, map); 
		  //recruitment=new  Recruitment_POM(driver);
		 // recruitment.enterCandiadateDetails(test);
		 
	
		
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
