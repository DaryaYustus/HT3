package com.epam.ta;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.pages.CreateNewRepositoryPage;
import com.epam.ta.pages.MainPage;
import com.epam.ta.steps.Steps;

public class GitHubAutomationTest {
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";
	private final int REPOSITORY_NAME_POSTFIX_LENGTH = 6;
	private final String REPOSITORY_NAME = "TEST_REPO";

	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.openBrowser();
	}

	@Test(description = "Check the create repository form is empty", enabled = true)
	public void createRepositoryFormIsEmpty() {
		steps.loginGithub(USERNAME, PASSWORD);
		CreateNewRepositoryPage createNewRepositoryPage = steps
				.clickCreateRepository();
		assertEquals(createNewRepositoryPage.getRepositoryName(), "");
		assertEquals(createNewRepositoryPage.getRepositoryDescription(), "");
		assertFalse(createNewRepositoryPage.isCreateButtonAvailable());
	}

	@Test(description = "Create repository button is available on the create repository form", enabled = true)
	public void createRepositoryFormIsNotEmpty() {
		steps.loginGithub(USERNAME, PASSWORD);
		CreateNewRepositoryPage createNewRepositoryPage = steps
				.fillCreareRepositoryForm(REPOSITORY_NAME, REPOSITORY_NAME);
		assertTrue(createNewRepositoryPage.isCreateButtonAvailable());
	}

	@Test(description = "Create new TEST_REPO repository", enabled = true)
	public void canCreateNewRepository() {

		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewRepository(REPOSITORY_NAME, "auto-generated test repo");
		Assert.assertEquals(steps.getCurrentRepositoryName(), REPOSITORY_NAME);
		String expectedHref = "https://github.com/" + USERNAME + "/"
				+ REPOSITORY_NAME;
		Assert.assertEquals(steps.getCurrentRepositoryHref(), expectedHref);
	}

	@Test(description = "Check ssh url for repository")
	// dependsOnMethods = "canCreateNewRepository")
	public void checkSSHUrl() {
		steps.loginGithub(USERNAME, PASSWORD);
		steps.openReposinoryPage(USERNAME, REPOSITORY_NAME);
		String expectedUrl = "git@github.com:" + USERNAME + "/"
				+ REPOSITORY_NAME + ".git";
		assertEquals(steps.getSSHUrl(), expectedUrl);
	}

	@Test(description = "Check ssh url for repository")
	// dependsOnMethods = "canCreateNewRepository")
	public void checkHHTPSUrl() {
		steps.loginGithub(USERNAME, PASSWORD);
		steps.openReposinoryPage(USERNAME, REPOSITORY_NAME);
		String expectedUrl = "https://github.com/" + USERNAME + "/"
				+ REPOSITORY_NAME + ".git";
		assertEquals(steps.getHTTPSUrl(), expectedUrl);
	}

	@Test(description = "Login to Github", enabled = true)
	public void oneCanLoginGithub() {
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertEquals(USERNAME, steps.getLoggedInUserName());
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		steps.closeBrowser();
	}

}
