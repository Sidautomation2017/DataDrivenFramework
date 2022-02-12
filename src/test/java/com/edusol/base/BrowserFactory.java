package com.edusol.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	public static String homepath = System.getProperty("user.dir");

	static WebDriver driver;

	public static WebDriver browserLaunch(String browserName, String url) {
		switch (browserName) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					homepath + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", homepath + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case "edge":
			System.setProperty("webdriver.edge.driver", homepath + "\\src\\test\\resources\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
			
		default :
		System.setProperty("webdriver.chrome.driver",
				homepath + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
				

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		return driver;
	}

}
