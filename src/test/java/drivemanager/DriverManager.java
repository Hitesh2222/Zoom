package drivemanager;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager instance;

    private DriverManager(){
        if(Config.getProperty("browser.name").equalsIgnoreCase(BrowserType.CHROME)){
           System.setProperty(BrowserType.CHROME_PROPERTY,Config.getProperty("chrome.executable"));
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Hitesh\\Desktop\\PRAGRA\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(Config.getProperty("browser.name").equalsIgnoreCase(BrowserType.FIREFOX)){
            System.setProperty(BrowserType.FIREFOX_PROPERTY, Config.getProperty("firefox.executable"));
            driver = new FirefoxDriver();
        }
        else {
            System.setProperty(BrowserType.CHROME_PROPERTY, Config.getProperty("chrome.executable"));
            driver = new ChromeDriver();
        }
    }
    private DriverManager(String browserName){
        if(browserName.equalsIgnoreCase(BrowserType.CHROME)){
              System.setProperty(BrowserType.CHROME_PROPERTY,Config.getProperty("chrome.executable"));
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Hitesh\\Desktop\\PRAGRA\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase(BrowserType.FIREFOX)){
            System.setProperty(BrowserType.FIREFOX_PROPERTY, Config.getProperty("firefox.executable"));
            driver = new FirefoxDriver();
        }
        else {
            System.setProperty(BrowserType.CHROME_PROPERTY, Config.getProperty("chrome.executable"));
            driver = new ChromeDriver();
        }
    }
    public synchronized static WebDriver getDriver(){
        if(instance == null){
            instance = new DriverManager();
        }
        return instance.driver;
    }
    public synchronized static WebDriver getDriver(String browserName){
        if(instance == null || instance.driver instanceof ChromeDriver && !browserName.equalsIgnoreCase("CHROME")){
            if(instance != null) instance.driver.quit();
            instance = new DriverManager(browserName);
        }
        return instance.driver;
    }
}
