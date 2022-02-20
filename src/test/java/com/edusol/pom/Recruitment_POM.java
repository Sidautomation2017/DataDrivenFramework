package com.edusol.pom;

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

	public void enterCandiadateDetails(ExtentTest test) throws InterruptedException {
		test.log(Status.INFO, "recruitmet started");
		recruit.click();
		
		CommonMethods.explicitwaitforCLickable(driver, jobtitle);		
		CommonMethods.selectDropdown_index(jobtitle, 0);
		CommonMethods.explicitwaitforCLickable(driver, vacancy);
		CommonMethods.selectDropdown_index(vacancy, 2);		
		CommonMethods.explicitwaitforCLickable(driver, hiringManager);
		CommonMethods.selectDropdown_index(hiringManager, 1);	
		CommonMethods.explicitwaitforCLickable(driver, status);
		CommonMethods.selectDropdown_value(status, "HIRED");	

		candidatename.click();
		candidatename.sendKeys("John Smith");
		Thread.sleep(2000);

		keyword.click();
		keyword.sendKeys("Selenium");
		Thread.sleep(2000);

		WebElement Appmethod = driver.findElement(By.name("candidateSearch[modeOfApplication]"));
		CommonMethods.selectDropdown_index(Appmethod, 0);

		Fromdate.click();
		Fromdate.sendKeys("2022-01-14");
		Fromdate.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		Todate.click();
		Todate.sendKeys("2022-01-15");
		Todate.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		Search.click();
		CommonMethods.explicitwaitforVisiblity(driver, resultTable);
		Assert.assertTrue(resultTable.isDisplayed());
		

	}

}
