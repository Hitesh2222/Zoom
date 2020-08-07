package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DataSciencePage {
    WebDriver driver;
    @FindBy(css = ".home-banner-left>h2")
    private WebElement heading;

    public DataSciencePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver,this);
    }
    public String hetH2Text(){
        return this.heading.getText();
    }
}
