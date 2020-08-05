package testcases;

import config.config;
import drivemanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest {
        private static Logger logger = LogManager.getLogger(HomeTest.class);
        @Test
        public void dummyTest(){
                logger.fatal("Here is I am with {} FATAL","Hitesh");
                logger.info("Here is I am with {} INFO","Hitesh");
                logger.error("Here is I am with {} ERROR","Hitesh");
                logger.debug("Here is I am with {} DEBUG","Hitesh");
                logger.trace("Here is I am with {} TRACE","Hitesh");
        }
        @Test
        public void testBrowserProperty() throws InterruptedException {
                DriverManager.getDriver().get(config.getProperty("app.url"));
                Thread.sleep(5000);
                DriverManager.getDriver().quit();
        }
}

