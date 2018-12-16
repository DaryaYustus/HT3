package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.ta.utils.Utils;

public class CreateNewRepositoryPage extends AbstractPage {
	private final String BASE_URL = "http://www.github.com/new";
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(id = "repository_name")
	private WebElement inputRepositoryName;

	@FindBy(id = "repository_description")
	private WebElement inputRepositoryDescription;

	@FindBy(xpath = "//form[@id='new_repository']//button[@type='submit']")
	private WebElement butttonCreate;

	@FindBy(xpath = "//h3/strong[text()='Quick setup']")
	private WebElement labelEmptyRepoSetupOption;

	@FindBy(xpath = "//a[@data-pjax='#js-repo-pjax-container']")
	private WebElement linkCurrentRepository;

	public CreateNewRepositoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public boolean isCurrentRepositoryEmpty() {
		return labelEmptyRepoSetupOption.isDisplayed();
	}

	public void createNewRepository(String repositoryName,
			String repositoryDescription) {
		fillCreateRepositoryForm(repositoryName, repositoryDescription);
		butttonCreate.click();
	}

	public void fillCreateRepositoryForm(String repositoryName,
			String repositoryDescription) {
		inputRepositoryName.sendKeys(repositoryName);
		inputRepositoryDescription.sendKeys(repositoryDescription);
		new WebDriverWait(driver,20).until(ExpectedConditions
				.elementToBeClickable(butttonCreate));
	}

	public String getRepositoryName() {
		return inputRepositoryName.getText();
	}

	public String getRepositoryDescription() {
		return inputRepositoryDescription.getText();
	}

	public boolean isCreateButtonAvailable() {
		return butttonCreate.isEnabled();
	}

	public String getCurrentRepositoryName() {
		return linkCurrentRepository.getText();
	}
	
	public String getCurrentRepositoryHref() {
		return linkCurrentRepository.getAttribute("href");
	}


	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

}
