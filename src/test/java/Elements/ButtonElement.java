package Elements;

import org.openqa.selenium.By;

public class ButtonElement {

    public static By getDefaultLocatorById(String id) {
        return By.xpath(".//button[contains(@id, '" + id + "')]");
    }

    public static By getDefaultLocatorByName(String name) {
        return By.xpath(".//button[contains(text(), '" + name + "')]");
    }

}