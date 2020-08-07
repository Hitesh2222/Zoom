package testcases;

import config.Config;
import drivemanager.DriverManager;
import listeners.ScreenshotListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DataSciencePage;
import pages.TopNavBar;
import utils.Utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listeners(ScreenshotListener.class)
public class HomeTest {
        private static Logger logger = LogManager.getLogger(HomeTest.class);
        WebDriver driver = DriverManager.getDriver();
        @Test
        public void dummyTest(){
                logger.fatal("Here is I am with {} FATAL","Hitesh");
                logger.info("Here is I am with {} INFO","Hitesh");
                logger.error("Here is I am with {} ERROR","Hitesh");
                logger.debug("Here is I am with {} DEBUG","Hitesh");
                logger.trace("Here is I am with {} TRACE","Hitesh");
        }
        @Test
        public void testBrowserProperty() throws InterruptedException, IOException {

                DriverManager.getDriver().get(Config.getProperty("app.url"));
//                Assert.assertEquals(driver.getTitle(),"Facebook");
                Thread.sleep(5000);

        }

        @Test
        public void testDataScienceH2() {
                DriverManager.getDriver().get(Config.getProperty("app.url"));
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                TopNavBar navBar = new TopNavBar(driver);
                DataSciencePage sciencePage = navBar.clickDataScience();
                Assert.assertEquals(sciencePage.hetH2Text(),"Now KickStart Data Scientist Career for just $99.");
        }

        @AfterSuite
        public void tearDown() throws InterruptedException {
                Thread.sleep(10000);
                DriverManager.getDriver().quit();
        }
}

