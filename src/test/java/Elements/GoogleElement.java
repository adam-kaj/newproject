package Elements;

import org.openqa.selenium.By;

public class GoogleElement {

    public static By DefaultGoogleLocator(String text) {
        return By.xpath(".//a[contains(text(),'" + text+ "'')]");
    }
}
