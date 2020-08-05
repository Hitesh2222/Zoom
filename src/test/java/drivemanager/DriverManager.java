package drivemanager;

import config.config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager instance;

    private DriverManager(){
        if(config.getProperty("browser.name").equalsIgnoreCase(BrowserType.CHROME)){
//            System.setProperty(BrowserType.CHROME_PROPERTY,config.getProperty("chrome.executable"));
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Hitesh\\Desktop\\PRAGRA\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(config.getProperty("browser.name").equalsIgnoreCase(BrowserType.FIREFOX)){
            System.setProperty(BrowserType.FIREFOX_PROPERTY,config.getProperty("firefox.executable"));
            driver = new FirefoxDriver();
        }
        else {
            System.setProperty(BrowserType.CHROME_PROPERTY,config.getProperty("chrome.executable"));
            driver = new ChromeDriver();
        }

    }
    public static WebDriver getDriver(){
        if(instance == null){
            instance = new DriverManager();
        }
        return instance.driver;
    }
}
