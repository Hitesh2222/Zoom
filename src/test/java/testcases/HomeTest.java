package testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import config.Config;
import data.ExcelDataManager;
import drivemanager.DriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import listeners.ScreenshotListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainNavBar;
import pages.TopNavBar;
import pages.contactSaleaPage;
import reports.HtmlReports;
import utils.Utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
//import static org.hamcrest.core.*;

@Listeners(ScreenshotListener.class)
public class HomeTest {
        private static Logger logger = LogManager.getLogger(HomeTest.class);
        WebDriver driver;

        MainNavBar mainNavBar;
        contactSaleaPage saleaPage;
//        @Test
//        public void dummyTest(){
//                logger.fatal("Here is I am with {} FATAL","Hitesh");
//                logger.info("Here is I am with {} INFO","Hitesh");
//                logger.error("Here is I am with {} ERROR","Hitesh");
//                logger.debug("Here is I am with {} DEBUG","Hitesh");
//                logger.trace("Here is I am with {} TRACE","Hitesh");
//        }
//        @Test
//        public void testBrowserProperty() throws InterruptedException, IOException {
//
//                DriverManager.getDriver().get(Config.getProperty("app.url"));
////                Assert.assertEquals(driver.getTitle(),"Facebook");
//                Thread.sleep(5000);
//
//        }
//
//        @Test
//        public void testDataScienceH2() {
//                DriverManager.getDriver().get(Config.getProperty("app.url"));
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        }

        @BeforeSuite
        public void setUp() {
                driver = DriverManager.getDriver();
                driver.get(Config.getProperty("app.url"));
//                WebDriverWait wait = new WebDriverWait(driver,10);
//                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("truste-consent-buttons"))).click();
        }

        @Test(enabled = true)
        public void testMeetingChat() {
                ExtentTest test = HtmlReports.createTest("testMeetingChat");
                test.log(Status.INFO,"initialized Menu Bar");
                mainNavBar = new MainNavBar(driver);
                test.log(Status.INFO,"Clicked Meeting");
                mainNavBar.clickOnMeeting();

                try {
                        Assert.assertEquals(driver.getTitle(),"Zoom Meetings - Zoom");
                        test.log(Status.PASS,"Title Matches");
                }catch (Exception ex){
                        test.log(Status.FAIL,"Title Does Not Matches");
                        throw ex;
                }
        }
        @Test(dataProvider = "contactProvider2",dataProviderClass = ExcelDataManager.class,enabled = false)
        public void checkContactSales(String email,String company,String firstName,String lastname){
                mainNavBar = new MainNavBar(driver);
                saleaPage = mainNavBar.clickOnContactSales();
                saleaPage
                        .keyInEmail(email)
                        .keyInCompanyName(company)
                        .keyInFirstName(firstName)
                        .keyInLastName(lastname);

                try {
                        Thread.sleep(2000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

        }

        @Test(dataProvider = "dummy",dataProviderClass = ExcelDataManager.class)
        public void testDummy(Object ...args) {
                for (Object ob: args){
                        System.out.println(ob);
                }

        }
        @Test
        public void getprogramList(){
                Response response = RestAssured.get("https://pragra.io/api/course-info/{id}","5f0b1d8216b5b97e87391483");
                String s = response.body().prettyPrint();

                ValidatableResponse res = RestAssured.when()
                        .get("https://pragra.io/api/course-info/{id}","5f0b1d8216b5b97e87391483")
                        .then();

                res.assertThat().statusCode(400);
//                res.body("courseCode",isEquals("DATA-SCIENCE"));
//                System.out.println(s);
        }

        @AfterTest
        public void tearDown() throws InterruptedException {
                Thread.sleep(10000);
                HtmlReports.flush();
                DriverManager.getDriver().quit();
        }
}

