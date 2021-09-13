package com.docker.hub.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is parent class of all framework setup operation
 * it contains properties file,excel reader,waiting statement,screenShoot etc.
 * @author tjaiswal
 *
 */

public class TestBase
{
    public static WebDriver driver;
    //String URL="";
    String browser = "chrome";
    protected static String downloadPath = "C:\\Users\\tjaiswal\\Downloads";


    public static final Logger logger = Logger.getLogger(TestBase.class.getName());

    public void init(String URL)
    {
        selectBrowser(browser);

        PropertyConfigurator.configure(System.getProperty("user.dir") + "//src//main//Configuration//loggingstatus.properties");
        getURL(URL);
    }


    public Properties getProp()
    {
        //String path1 = System.getProperty("");
        File file = new File(System.getProperty("user.dir") + "//src//main//Configuration//projectconfig.properties");
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try
        {
            properties.load(fileInputStream);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return properties;
    }

    public void selectBrowser(String browser)

    {
        if(browser.equalsIgnoreCase("firefox"))
        {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.download.dir", downloadPath);
            profile.setPreference("browser.helperApps.neverAsk.openFile",
                    "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                    "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
            profile.setPreference("browser.download.manager.focusWhenStarting", false);
            profile.setPreference("browser.download.manager.useWindow", false);
            profile.setPreference("browser.download.manager.showAlertOnComplete", false);
            profile.setPreference("browser.download.manager.closeWhenDone", false);

            DesiredCapabilities ds = new DesiredCapabilities().firefox();
            ds.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            ds.setCapability(FirefoxDriver.PROFILE, profile);


            // this is the important line - i.e. don't use Marionette
            ds.setCapability(FirefoxDriver.MARIONETTE, false);

            driver = new FirefoxDriver(ds);
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//main//Configuration//gekodriver.exe");
            logger.info("Firefox has launching...");

        }
        else if(browser.equalsIgnoreCase("chrome"))
        {
            DesiredCapabilities ds1 = new DesiredCapabilities().chrome();
            ds1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src/main//Configuration//chromedriver.exe");
            driver = new ChromeDriver(ds1);
            logger.info("chrome browser has launching..");

        }

    }

    public void getURL(String URL)
    {
        driver.get(URL);
        logger.info("url has lauching..");
        driver.manage().window().maximize();
        logger.info("Now Browser is maximize...");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        logger.info("Page loading.....");
    }

    /**
     * This method is used for waitforElement has to load in page
     * @param timeOutInSeconds
     * @param element
     */

    public void waitforElement(int timeOutInSeconds, WebElement element)

    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    /**
     * This method is used for wait to process some time
     * @param sleepTimeOutInSeconds
     */

    public static void sleepTime(int sleepTimeOutInSeconds)
    {
        try
        {
            Thread.sleep(sleepTimeOutInSeconds*1000);
        }
        catch(InterruptedException e)
        {

            e.printStackTrace();
        }
    }

    public Timeouts ownImplicitWait(long time)
    {
        Timeouts implicitWait = driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        return implicitWait;
    }


    /**
     * This method is used for take a screen shot through webdriver with format of current time instance
     * @param name
     */

    public void quitDriver()
    {
        driver.quit();
    }
}

