package Pages;
import PageFactory.GooglePage;
import org.openqa.selenium.*;



public class Pages {
    private final WebDriver driver;

    private GooglePage googlePage;

    public Pages(WebDriver driver){
        this.driver = driver;
            }


    public GooglePage googlePage(){
        if(googlePage == null){
            googlePage = new GooglePage(driver);}
    return googlePage;
    }
}

