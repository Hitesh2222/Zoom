package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopNavBar {
    private WebDriver driver;

    @FindBy(css = ".mat-toolbar>span>a>img.logo")

    private WebElement logo;

    @FindBy(css = "ul.nav>li>a[href = '/workshop']")
    private WebElement dataScienceLink;

    @FindBy(linkText = "programs")
    private WebElement programLink;

    public TopNavBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public DataSciencePage clickDataScience(){
        this.dataScienceLink.click();
        return new DataSciencePage(driver );
    }
}
