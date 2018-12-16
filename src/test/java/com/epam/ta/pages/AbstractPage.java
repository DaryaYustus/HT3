package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage
{
	protected WebDriver driver;

	public abstract void openPage();

	public AbstractPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
    public void waitForElementPresent(WebElement element){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(element));
    }
}
