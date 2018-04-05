package Pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AbstractPage extends WebDriverPage {

    private final static int MENU_HEIGHT = 32;

    protected final static int LONG_TIME_WAITING_IN_SECONDS = 10;
    protected final static int MEDIUM_TIME_WAITING_IN_SECONDS = 5;
    protected final static int SHORT_TIME_WAITING_IN_SECONDS = 2;

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

    @Override
    protected WebDriverProvider getDriverProvider() {
        return super.getDriverProvider();
    }

    protected WebDriver getDriver() {
        return getDriverProvider().get();
    }

    public AbstractPage(final WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    /**
     * Szuka elementu po zakonczeniu dzialania loadera.
     *
     * @param locator lokator elementu
     */
    protected WebElement findElementAfterLoader(By locator) throws InterruptedException {
        return super.findElement(locator);
    }

    /**
     * Szuka elementow po zakonczeniu dzialania loadera.
     *
     * @param locator lokator elementow
     */
    protected List<WebElement> findElementsAfterLoader(By locator) throws InterruptedException {
        return super.findElements(locator);
    }


    /**
     * Czeka warunkowo na klikalny element przez okreslony czas.
     * Czas okreslony w elemencie WebDriverWait.
     *
     * @param locator - lokator elementu, na ktorego widocznosc czekamy
     */
    protected void waitForVisibilityOfElement(By locator) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this, LONG_TIME_WAITING_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * W przypadku gdy lokator moze byc nieunikalny wybiera pierwszy z elementow, ktory jest widoczny.
     *
     * @param by lokator elementu
     */
    protected WebElement findDisplayedElement(By by) throws InterruptedException {
        for (WebElement element : findElementsAfterLoader(by)) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        return findElement(by);
    }

    /**
     * Klika w podany element (jesli element nieklikalny - czekamy na niego przez okreslony czas).
     *
     * @param element - klikalny element
     */
    protected void clickElement(WebElement element) throws InterruptedException {
        waitForClickableElement(element);
        clickWithScrollIfNeeded(element);
    }

    /**
     * Klika w podany element (jesli element nieklikalny - czekamy na niego przez okreslony czas).
     *
     * @param by - lokator klikalnego elementu
     */
    protected void clickElementByLocator(By by) throws InterruptedException {
        waitForClickableElement(by, LONG_TIME_WAITING_IN_SECONDS);
        clickWithScrollIfNeeded(by);
    }

    /**
     * Klika w podany element (jesli element nieklikalny - czekamy na niego przez okreslony czas).
     *
     * @param by          - lokator klikalnego elementu
     * @param waitingTime - czas oczekiwania w sekundach
     */
    protected void clickElementByLocator(By by, int waitingTime) throws InterruptedException {
        waitForClickableElement(by, waitingTime);
        clickWithScrollIfNeeded(by);
    }

    /**
     * Klika w podany element szukając w zakresie(jesli element nieklikalny - czekamy na niego przez okreslony czas).
     *
     * @param by         - lokator klikalnego elementu
     * @param webElement - element bedacy zakresem wyszukiwania
     */
    protected void clickElementByLocator(By by, WebElement webElement) throws InterruptedException {
        waitForClickableElement(by, webElement, LONG_TIME_WAITING_IN_SECONDS);
        clickWithScrollIfNeeded(by, webElement);
    }

    /**
     * Ustawia sie na elemencie o wskazany identyfikatorze.
     *
     * @param element - element do ustawienia focus-a
     */
    protected void focusOnElement(WebElement element) throws InterruptedException {
        new Actions(getDriver()).moveToElement(element).perform();
    }

    /**
     * Ustawia na glownej ramce.
     */
    public void getMainFrame() throws InterruptedException {
        if (getPageSource().contains("mainFrame")) {
            switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(this, LONG_TIME_WAITING_IN_SECONDS);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("main"));
        }
    }

    private void waitForVisibilityOfElement(WebElement element) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this, SHORT_TIME_WAITING_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForClickableElement(WebElement element) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this, LONG_TIME_WAITING_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForClickableElement(By locator, int waitingTime) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this, waitingTime);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitForClickableElement(By locator, WebElement webElement, int waitingTime) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this, waitingTime);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        wait.until(ExpectedConditions.elementToBeClickable(webElement.findElement(locator)));
    }

    private void scrollElementToView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriverProvider().get();
        js.executeScript(String.format("window.scroll(0, %s)", element.getLocation().getY() - MENU_HEIGHT));
        hideExtendedElements(js);
    }

    private void hideExtendedElements(JavascriptExecutor js) {
        js.executeScript("var menu = document.getElementById('pmenu'); if(menu) menu.className='products-menu products-menu--hide';");
        js.executeScript("var config = document.getElementsByClassName('configuration-hover'); if(config.length > 0) config[0].className='ng-hide';");
    }

    private void clickWithScrollIfNeeded(By by) throws InterruptedException {
        try {
            findElement(by).click();
        } catch (WebDriverException e) {
            scrollElementToView(findElement(by));
            waitForClickableElement(findElement(by));
            findElement(by).click();
        }
    }

    private void clickWithScrollIfNeeded(By by, WebElement webElement) throws InterruptedException {
        try {
            webElement.findElement(by).click();
        } catch (WebDriverException e) {
            scrollElementToView(webElement.findElement(by));
            waitForClickableElement(webElement.findElement(by));
            webElement.findElement(by).click();
        }
    }

    private void clickWithScrollIfNeeded(WebElement element) throws InterruptedException {
        try {
            element.click();
        } catch (WebDriverException e) {
            scrollElementToView(element);
            waitForClickableElement(element);
            element.click();
        }
    }
}