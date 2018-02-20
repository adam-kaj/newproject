package Steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class GoogleStep extends Steps{
 public WebDriver driver;
    @BeforeScenario
    public void scenarioSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }
 @Given("web $parameter")
    public void openWeb(@Named("parameter") String url){

     /*Zmaksymalizuj okno przeglądarki*/
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    //driver.close();
     try {
         Thread.sleep(1000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
     /*Przejdz do google*/
    driver.get("parameter");
     try {
         Thread.sleep(1000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }



}
