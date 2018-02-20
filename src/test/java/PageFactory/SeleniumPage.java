package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumPage {
    public WebDriver driver;
    @FindBy(id = "inputSearch")
    public WebElement HelionSearchbox;

    @FindBy(xpath = "//*[@id=\"szukanie\"]/fieldset/a/button")
    public WebElement SeleniumSearchButton;


    public SeleniumPage(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);
    }
    public void setSearchText(String searchText){

        HelionSearchbox.sendKeys(searchText);

    }
    public void clickSearchButton(){

        SeleniumSearchButton.click();

    }

}