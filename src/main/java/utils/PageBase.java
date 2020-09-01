package utils;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PageBase {
	private static WebDriver driver;

    private static String baseUrl = "http://192.168.1.40:3000/#/login";

	private static String webDriverLocation = "src"+File.separator+"test"+File.separator+"resources"+File.separator+"drivers"+File.separator;
    protected static String downloadFilepath = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"fileDownload";
    protected static String uploadFilepath = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"fileUpload";
    protected static String osType = System.getProperty("os.type", Constants.WINDOWS);
    protected static String driverType = System.getProperty("browser.type", Constants.FIREFOX);

	/**
     * Initialize webdriver, set driver path and maximize chrome browser window
     */
    public static void initiateDriver() throws MalformedURLException {
        staticWait(1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        getDriver().manage().window().maximize();
        getDriver().get(baseUrl);
    }

    /*
     * Get web driver instance
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Close web driver instances
     */
    public static void closeDriver() {
        getDriver().quit();
        staticWait(1);
    }
    
    /**
     * Refresh web driver instances
     */
    public static void refreshDriver() {
        getDriver().navigate().refresh();
    }
    
    /**
     * Navigate Back
     */
    public static void navigateBack() {
        getDriver().navigate().back();
    }

    /**
     * GetCurrent Window Details
     */
    public static String getCurrentWindow() {
        return getDriver().getWindowHandle();
    }


    /* To Press ENTER Key using Robot */
    public static void hitEnter() throws Exception
    {
        Robot re = new Robot();
        re.keyPress(KeyEvent.VK_ENTER);
        re.keyRelease(KeyEvent.VK_ENTER);
    }
    public static void navigateToWindow(String windowName) {
        getDriver().switchTo().window(windowName);
    }
    
    /**
     * Static Wait
     */
    public static void staticWait(int seconds) {
    	try {
			Thread.sleep(seconds*1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Implicit Wait
     */
    public static void implicitWait(int seconds) {
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
    
    /**
     * Explicit Wait Clickable
     */
    public static void waiTillClickable(By element , int seconds) {
    	WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Explicit Wait Visible
     */
    public static void waiTillVisible(By element ,int seconds) {
    	WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
