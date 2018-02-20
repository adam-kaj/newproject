package NewProject.newproject;
import PageFactory.GooglePage;
import PageFactory.SeleniumPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

//import org.junit.After;
//import org.openqa.selenium.WebDriver.Options;
//import org.openqa.selenium.firefox.FirefoxDriver;


public class googleSearchTest {

   public WebDriver driver;
   GooglePage objGooglePage;
   SeleniumPage objSeleniumPage;

	@Before
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\drivers\\chromedriver.exe");


		/*Uruchom chrome*/
		driver = new ChromeDriver();
		 Thread.sleep(1000);
		/*Zmaksymalizuj okno przeglądarki*/
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.close();
		Thread.sleep(1000);
		/*Przejdz do google*/
		driver.get("http://www.google.pl");
		Thread.sleep(1000);
		
		 	}
	@Test
	public void TestGoogleSearch() {
		//Znajdz pole tekstowe po id
	/*	WebElement element = driver.findElement(By.name("q"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
        objGooglePage = new GooglePage(driver);
		//wpisz coś do wyszukiwarki
        objGooglePage.setSearchText("Testowanie z Selenium. Receptury");
        objGooglePage.clickSearchButton();
		WebElement first = driver.findElement(By.partialLinkText("Selenium i testowanie"));
		first.click();
		// Strona wyszukiwania w Google jest dynamicznie renderowana za pomocą JavaScript.
		// Czekamy na załadowanie się strony. Timeout po 10 sekundach.
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id<locator>));
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("Selenium i testowanie aplikacji. Receptury. Wydanie II Książka, kurs - Unmesh Gundecha - Wydawnictwo Helion, księgarnia internetowa informatyczna helion.pl",
               driver.getTitle());
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        objSeleniumPage = new SeleniumPage(driver);
        objSeleniumPage.setSearchText("coś");
		objSeleniumPage.clickSearchButton();
			}
	//@After
	//public void tearDown() throws Exception {
    //driver.quit();}

	}
