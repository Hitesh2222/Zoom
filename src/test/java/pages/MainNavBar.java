package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class MainNavBar {

    private WebDriver driver;

    @FindBy(css = "//*[contains(@class,'navbar-header')]//img[@class='logo']")
    private WebElement logo;

    @FindBy(css = "#solutionsDropdown>li:nth-child(1)>a")
    private WebElement meetingAndSolution;

    @FindBy(linkText = "Contact Sales")
    private WebElement contactSales;

    @FindBy(id = "btnSolutions")
    private WebElement btnSolutions;

    public MainNavBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnLogo(){
        this.logo.click();
    }
    public void clickOnMeeting(){
        Actions actions = new Actions(driver);
//        actions
//                .moveToElement(btnSolutions)
//                .pause(1000)
//                .moveToElement(meetingAndSolution)
//                .click()
//                .perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",meetingAndSolution);
    }
    public contactSaleaPage clickOnContactSales(){
        this.contactSales.click();
        return new contactSaleaPage(driver);
    }

}

/*
//*[contains(@class,'navbar-header')]//img[@class='logo']
 */
