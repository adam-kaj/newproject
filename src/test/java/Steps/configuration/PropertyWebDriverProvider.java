package Steps.configuration;


import org.jbehave.web.selenium.DelegatingWebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Locale;

public class PropertyWebDriverProvider extends DelegatingWebDriverProvider {

    public enum Browser {
        CHROME, FIREFOX, SAFARI, IE, PHANTOM
    }

    public void initialize() {
        Browser browser = Browser.valueOf(Browser.class,
                System.getProperty("browser", "chrome").toUpperCase(usingLocale()));
        delegate.set(createDriver(browser));
    }

    private WebDriver createDriver(Browser browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                driver = createChromeDriver();
                break;
            }
        return driver;
    }

    private ChromeDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }


 //       final String ffPath = FilePropertiesUtils.getDriversProperty("fireFoxPath");
   //     System.setProperty("webdriver.gecko.driver", FilePropertiesUtils.getDriversProperty("firefoxDriver"));
     //   if (ffPath != null) {
       //     System.setProperty("webdriver.firefox.bin", ffPath);
 //       }
 //       return new FirefoxDriver();
 //   }
    private Locale usingLocale() {
        return Locale.getDefault();
    }

}