package com.epam.ta.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali_Shulha on 20-Oct-16.
 */
public class DriverSingleton {

	private static WebDriver driver;
	private static final Logger logger = LogManager.getRootLogger();

	// for Firefox
	private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
	private static final String GECKODRIVER_GECKODRIVER_EXE_PATH = ".\\geckodriver\\geckodriver.exe";
	// for Google Ghrome
	private static final String CHROMEDRIVER_EXE_PATH = ".\\chromedriver\\chromedriver.exe";
	public static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";

	private DriverSingleton() {
	};

	public static WebDriver getDriver() {
		if (null == driver) {
			System.setProperty(WEBDRIVER_CHROME, CHROMEDRIVER_EXE_PATH);
			driver = new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Browser started");
		}

		return driver;
	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
