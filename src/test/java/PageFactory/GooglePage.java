package PageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage {

       @FindBy(name = "q")
    public WebElement StartPageSearchPlaceWebElement;

    @FindBy(name="btnK")
     public WebElement SearchButtonWebElement;
    public GooglePage(WebDriver driver){

    }
    public void setSearchText(String searchText){

        StartPageSearchPlaceWebElement.sendKeys(searchText);

    }
    public void clickSearchButton(){

        SearchButtonWebElement.click();

    }

}