package com.docker.hub.pageobects;

import com.docker.hub.utility.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class DockerHubSearch extends TestBase
{
    public static final Logger logger = Logger.getLogger(DockerHubSearch.class
            .getName());
    private static final String ARGUMENTS_0_CLICK = "arguments[0].click()";

    public DockerHubSearch(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@value='store']")
    WebElement verifierCheckbox;

    @FindBy(xpath = "//input[@placeholder='Search for great content (e.g., mysql)']")
    WebElement searchBox;

    @FindBy(xpath = "//input[@value='store']/parent::div/div/label/span ")
    WebElement verifiedPublisherLabel;

    @FindBy(xpath = "//div[normalize-space()='Images']")
    WebElement imagesLabel;

    @FindBy(xpath = "//input[@value='official']/parent::div/div/label/span[1] ")
    WebElement officalImagesLabel;

    @FindBy(xpath = "//button[normalize-space()='Containers']")
    WebElement containersLink;

    @FindBy(xpath = "//input[@value='analytics']/parent::div/div/label/parent::div/div")
    WebElement analyticsCheckBox;

    @FindBy(xpath = "//input[@value='base']/parent::div/div/label/parent::div/div")
    WebElement baseImagesCheckBox;

    @FindBy(xpath = "//input[@value='database']/parent::div/div/label/parent::div/div")
    WebElement databaseCheckBOx;

    @FindBy(xpath = "//input[@value='storage']/parent::div/div/label/parent::div/div")
    WebElement storageCheckBox;

    @FindBy(xpath = "//div[@data-testid='currentFilters']/div")
    WebElement publisherContentElement;

    @FindBy(xpath = "//input[@value='base']")
    WebElement baseImagesOption;

    @FindBy(xpath = "//input[@value='database']")
    WebElement databasesOption;

    @FindBy(xpath = "//div[@data-testid='currentFilters']/div[2]")
    WebElement databaseFilter;

    @FindBy(xpath = "//div[@data-testid='currentFilters']/div[1]")
    WebElement baseImagesFilter;

    public boolean verifyContainerPage() throws InterruptedException
    {
        Thread.sleep(40);

        if(containersLink.isDisplayed())
        {
            return true;
        }
        return false;
    }

    public List<String> verifyLabel() throws InterruptedException
    {
        Thread.sleep(40);
        ArrayList<String> labelList = new ArrayList<String>();

        String imagesLabelText = imagesLabel.getText();
        labelList.add(imagesLabelText);

        String verifiedPublishedImagesText = verifiedPublisherLabel.getText();
        labelList.add(verifiedPublishedImagesText);

        String officialPublishedImagesText = officalImagesLabel.getText();
        labelList.add(officialPublishedImagesText);
        return labelList;
    }

    public List<Boolean> verifyCatergories() throws InterruptedException
    {
        Thread.sleep(40);
        ArrayList<Boolean> categoriesList = new ArrayList<Boolean>();

        categoriesList.add(analyticsCheckBox.isDisplayed());
        categoriesList.add(baseImagesCheckBox.isDisplayed());
        categoriesList.add(databaseCheckBOx.isDisplayed());
        categoriesList.add(storageCheckBox.isDisplayed());
        return categoriesList;
    }

    public String verifiedPublisherContent() throws InterruptedException
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript(ARGUMENTS_0_CLICK, verifierCheckbox);
        Thread.sleep(20);
        return publisherContentElement.getText();
    }

    public List<String> verifiedBaseImagesAndDatabaseOptions() throws InterruptedException
    {
        ArrayList<String> imagesContents = new ArrayList<String>();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript(ARGUMENTS_0_CLICK, baseImagesOption);
        jse.executeScript(ARGUMENTS_0_CLICK, databasesOption);
        Thread.sleep(20);
        imagesContents.add(baseImagesFilter.getText());
        imagesContents.add(databaseFilter.getText());
        return imagesContents;
    }

    public Boolean verifiedBaseImagesFilterRemoved() throws InterruptedException
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript(ARGUMENTS_0_CLICK, baseImagesFilter);
        Thread.sleep(20);
        return baseImagesOption.isSelected();
    }
}

