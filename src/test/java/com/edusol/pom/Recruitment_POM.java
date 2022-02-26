package com.edusol.pom;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.edusol.base.CommonMethods;

public class Recruitment_POM {

	// objects locators

	WebDriver driver;

	@FindBy(css = "#menu_recruitment_viewRecruitmentModule")
	WebElement recruit;

	@FindBy(css = "#candidateSearch_jobTitle")
	WebElement jobtitle;
	
	
	@FindBy(name = "candidateSearch[jobVacancy]")
	WebElement vacancy;

	@FindBy(name = "candidateSearch[hiringManager]")
	WebElement hiringManager;

	@FindBy(css = "#candidateSearch_status")
	WebElement status;

	@FindBy(name = "candidateSearch[candidateName]")
	WebElement candidatename;

	@FindBy(name = "candidateSearch[keywords]")
	WebElement keyword;

	@FindBy(name = "candidateSearch[modeOfApplication]")
	WebElement Appmethod;

	@FindBy(name = "candidateSearch[dateApplication][from]")
	WebElement Fromdate;

	@FindBy(name = "candidateSearch[dateApplication][to]")
	WebElement Todate;

	@FindBy(id = "btnSrch")
	WebElement Search;
	

	@FindBy(id = "resultTable")
	WebElement resultTable;
	
	

	public Recruitment_POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterCandiadateDetails(ExtentTest test, Map<String, String> data) throws InterruptedException {
		
		if(data.containsKey("Recruitment_Candidates") && data.get("Recruitment_Candidates").equalsIgnoreCase("yes")) {
		
		test.log(Status.INFO, "recruitmet started");
		recruit.click();
		
		CommonMethods.explicitwaitforCLickable(driver, jobtitle);		
		CommonMethods.selectDropdown_index(jobtitle, Integer.parseInt(data.get("JobTitle")));
		CommonMethods.explicitwaitforCLickable(driver, vacancy);
		CommonMethods.selectDropdown_index(vacancy,  Integer.parseInt(data.get("Vacancy")));		
		CommonMethods.explicitwaitforCLickable(driver, hiringManager);
		CommonMethods.selectDropdown_index(hiringManager, Integer.parseInt(data.get("HiringManager")));	
		CommonMethods.explicitwaitforCLickable(driver, status);
		CommonMethods.selectDropdown_value(status, data.get("Staus"));	

		candidatename.click();
		candidatename.sendKeys(data.get("CandidateName"));
		Thread.sleep(2000);

		keyword.click();
		keyword.sendKeys(data.get("Keywords"));
		Thread.sleep(2000);

		CommonMethods.selectDropdown_index(Appmethod, Integer.parseInt(data.get("Keywords")));

		Fromdate.click();
		Fromdate.sendKeys(data.get("FromDate"));
		Fromdate.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		Todate.click();
		Todate.sendKeys(data.get("ToDate"));
		Todate.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		Search.click();
		CommonMethods.explicitwaitforVisiblity(driver, resultTable);
		Assert.assertTrue(resultTable.isDisplayed());
		test.log(Status.PASS, "recruitmet search is sucessfull ");
		
		}
	}

}
