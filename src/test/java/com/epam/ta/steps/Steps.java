package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.utils.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.ta.pages.AbstractPage;
import com.epam.ta.pages.CreateNewRepositoryPage;
import com.epam.ta.pages.LoginPage;
import com.epam.ta.pages.MainPage;
import com.epam.ta.pages.RepositoryPage;

public class Steps {
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void openBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeBrowser() {
		DriverSingleton.closeDriver();
	}

	public void loginGithub(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public String getLoggedInUserName() {
		LoginPage loginPage = new LoginPage(driver);
		return loginPage.getLoggedInUserName().trim().toLowerCase();
	}

	public void createNewRepository(String repositoryName,
			String repositoryDescription) {
		CreateNewRepositoryPage createNewRepositoryPage = clickCreateRepository();
		createNewRepositoryPage.createNewRepository(repositoryName,
				repositoryDescription);
	}

	public CreateNewRepositoryPage fillCreareRepositoryForm(
			String repositoryName, String repositoryDescription) {
		CreateNewRepositoryPage createNewRepositoryPage = clickCreateRepository();
		createNewRepositoryPage.fillCreateRepositoryForm(repositoryName,
				repositoryDescription);
		return createNewRepositoryPage;
	}

	public CreateNewRepositoryPage clickCreateRepository() {
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		return new CreateNewRepositoryPage(driver);
	}

	public boolean currentRepositoryIsEmpty() {
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(
				driver);
		return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

	public String getCurrentRepositoryName() {
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(
				driver);
		return createNewRepositoryPage.getCurrentRepositoryName();
	}

	public String getCurrentRepositoryHref() {
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(
				driver);
		return createNewRepositoryPage.getCurrentRepositoryHref();
	}

	public String generateRandomRepositoryNameWithCharLength(int howManyChars) {
		String repositoryNamePrefix = "testRepo_";
		return repositoryNamePrefix.concat(Utils.getRandomString(howManyChars));
	}

	public void openReposinoryPage(String username, String repository) {
		new RepositoryPage(driver).openPage(username, repository);
	}

	public String getSSHUrl() {
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		repositoryPage.clickSSHButton();
		return repositoryPage.getCloneUrl();
	}

	public String getHTTPSUrl() {
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		repositoryPage.clickHTTPSButton();
		return repositoryPage.getCloneUrl();
	}
}
