package PageFactory;

import Pages.AbstractPage;
import Pages.ButtonPage;
import Pages.GooglePage;
import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {
    private final WebDriverProvider driver;

    private GooglePage googlePage;
    private ButtonPage buttonPage;
    private AbstractPage abstractPage;

    public PageFactory(WebDriverProvider driver){
        this.driver = driver;
    }


    public GooglePage getGooglePage(){
        if(googlePage == null){
            googlePage = new GooglePage(driver);}
        return googlePage;
    }
    public ButtonPage getButtonPage(){
        if(buttonPage == null){
            buttonPage = new ButtonPage(driver);}
        return buttonPage;
    }
    public AbstractPage getAbstractPage(){
        if(abstractPage == null){
            abstractPage = new AbstractPage(driver);}
        return abstractPage;
    }
}

