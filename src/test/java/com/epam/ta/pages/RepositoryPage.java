package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RepositoryPage extends AbstractPage {

	private final String BASE_URL = "https://github.com/";

	@FindBy(xpath = "//button[contains(text(), 'SSH')]")
	private WebElement buttonSSH;

	@FindBy(xpath = "//button[contains(text(), 'HTTPS')]")
	private WebElement buttonHTTPS;

	@FindBy(xpath = "//input[@id='empty-setup-clone-url']")
	private WebElement cloneUrl;

	public RepositoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		openPage("testadmin", "testrepository");
	}

	public void openPage(String username, String repository) {
		String repositoryUrl = BASE_URL + username + "/" + repository;
		driver.navigate().to(repositoryUrl);
	}

	public void clickSSHButton() {
		buttonSSH.click();
	}

	public void clickHTTPSButton() {
		buttonHTTPS.click();
	}

	public String getCloneUrl() {
		return cloneUrl.getAttribute("value");
	}

}
