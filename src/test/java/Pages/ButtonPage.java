package Pages;

import Elements.ButtonElement;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class ButtonPage extends AbstractPage {

    public ButtonPage(WebDriverProvider driver) {
        super(driver);
    }

    /**
     * Klika w button odnaleziony po identyfikatorze.
     *
     * @param id identyfikator elementu
     * @throws InterruptedException
     */
    public void clickButtonById(String id) throws InterruptedException {
        clickElementByLocator(By.id(id));
    }

    /**
     * Klika w button odnaleziony po fragmencie identyfikatora.
     *
     * @param id fragment identyfikatora elementu
     * @throws InterruptedException
     */
    public void clickButtonContainsId(String id) throws InterruptedException {
        clickElement(findDisplayedElement(ButtonElement.getDefaultLocatorById(id)));
    }

    /**
     * Klika w button odnaleziony po nazwie
     *
     * @param name nazwa elementu badz jej fragment
     * @throws InterruptedException
     */
    public void clickButtonContainsName(String name) throws InterruptedException {
        clickElementByLocator(ButtonElement.getDefaultLocatorByName(name));
    }

}