package Pages;

import Elements.GoogleElement;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GooglePage extends AbstractPage{

    public GooglePage(WebDriverProvider driver) {
        super(driver);
    }

    /**
     * wpisuje frazę w google
     *
     * @param searchText szukana fraza
     * @throws InterruptedException
     */
    public void setSearchText(final String searchText) throws InterruptedException{
        WebElement search = findElement(By.name("q"));
        search.clear();
        search.sendKeys(searchText);
    }
    /**
     * wyszukuje frazę w google
     *
     * klika w button wyszukaj
     * @throws InterruptedException
     */
    public void clickSearchButton() throws InterruptedException {
        WebElement submit = findElement(By.name("btnK"));
        clickElement(submit);
    }
    /**
     * otwiera żądaną stronę
     *
     * @param url adres strony
     * @throws InterruptedException
     */
    public void openWeb(final String url) throws InterruptedException{
        get(url);
    }
    /**
     * wybiera element wyszukiwań z listy Google zawierający text
     *
     * @param text
     * @throws InterruptedException
     */
    public void clickGoogleListElement(final String text) throws InterruptedException{
        clickElementByLocator(GoogleElement.DefaultGoogleLocator(text));
    }
}