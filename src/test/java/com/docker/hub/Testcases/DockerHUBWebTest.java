package com.docker.hub.Testcases;

import com.docker.hub.pageobects.DockerHubSearch;
import com.docker.hub.utility.TestBase;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

public class DockerHUBWebTest extends TestBase
{
    DockerHubSearch dockerHubSearch;
    public static final Logger logger = Logger.getLogger(DockerHUBWebTest.class.getName());

    @BeforeTest
    public void setUp()
    {
        Properties properties = getProp();
        String URL1 = properties.getProperty("URL");
        init(URL1);
    }

    @Test(enabled = true, priority = 1, description = "THis is validate container page is visible")
    @Severity(SeverityLevel.CRITICAL)
    public void testContainerPage() throws InterruptedException
    {
        logger.info("===Started Verifying testContainerPage=====");
        dockerHubSearch = new DockerHubSearch(driver);
        Assert.assertTrue(dockerHubSearch.verifyContainerPage());
        logger.info("===Finished Verifying testContainerPage=====");
    }


    @Test(enabled = true, priority = 2,description = "This is verify label")
    @Severity(SeverityLevel.MINOR)
    public void testVerifyLabel() throws InterruptedException
    {
        logger.info("===Started Verifying testVerifyLabel=====");
        dockerHubSearch = new DockerHubSearch(driver);
        List<String> verifyLabelList = dockerHubSearch.verifyLabel();
        Assert.assertEquals(verifyLabelList.get(0), "Images");
        Assert.assertEquals(verifyLabelList.get(1), "Verified Publisher");
        Assert.assertEquals(verifyLabelList.get(2), "Official Images");
        logger.info("===Finished Verifying testVerifyLabel=====");
    }

    @Test(priority = 3,description = "This is validate categories")
    @Severity(SeverityLevel.BLOCKER)
    public void testVerifyCategories() throws InterruptedException
    {
        logger.info("===Started Verifying testVerifyCategories=====");
        dockerHubSearch = new DockerHubSearch(driver);
        List<Boolean> verifyCategories = dockerHubSearch.verifyCatergories();
        for(boolean verifyCategory : verifyCategories)
        {
            System.out.println(verifyCategory);
            Assert.assertTrue(verifyCategory);
        }
        logger.info("===Finished Verifying testVerifyCategories=====");
    }

    @Test(priority = 4,description = "VErify the publisher content")
    @Severity(SeverityLevel.NORMAL)

    public void testPublisherContent() throws InterruptedException
    {
        logger.info("===Started Verifying testPublisherContent=====");
        dockerHubSearch = new DockerHubSearch(driver);
        Assert.assertEquals(dockerHubSearch.verifiedPublisherContent(), "Publisher Content");
        logger.info("===Finished Verifying testPublisherContent=====");
    }

    @Test(priority = 5,description = "This is verify the databse and base image validation")
    @Severity(SeverityLevel.BLOCKER)
    public void testBaseImageAndDatabaseFilterContent() throws InterruptedException
    {
        logger.info("===Started Verifying testBaseImageAndDatabaseFilterContent=====");
        dockerHubSearch = new DockerHubSearch(driver);
        List<String> baseImagesAndDatabaseList = dockerHubSearch.verifiedBaseImagesAndDatabaseOptions();
        Assert.assertEquals(baseImagesAndDatabaseList.get(0), "Base Images");
        Assert.assertEquals(baseImagesAndDatabaseList.get(1), "Databases");
        logger.info("===Finished Verifying testBaseImageAndDatabaseFilterContent=====");
    }

    @Test(priority = 6,description = "Base image removal from left pane")
    @Severity(SeverityLevel.CRITICAL)
    public void testBaseImageFilterRemovedFromLeftPane() throws InterruptedException
    {
        logger.info("===Started Verifying testBaseImageFilterRemovedFromLeftPane=====");
        dockerHubSearch = new DockerHubSearch(driver);
        Assert.assertFalse(dockerHubSearch.verifiedBaseImagesFilterRemoved());
        logger.info("===Finished Verifying testBaseImageFilterRemovedFromLeftPane=====");
    }

    @AfterTest
    public void tearDown()
    {
        quitDriver();
    }

}
