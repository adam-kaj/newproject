package Steps.configuration;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.web.selenium.PropertyWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    public class ConfigurationWebDriverSteps {
        private long testStartTime;

        private static Logger LOGGER = LoggerFactory.getLogger(ConfigurationWebDriverSteps.class);

        private WebDriverProvider driverProvider;

        public ConfigurationWebDriverSteps(PropertyWebDriverProvider pDriverProvider) {
            this.driverProvider = pDriverProvider;
        }

        @BeforeStory
        public void beforeStory() {
            driverProvider.initialize();
            driverProvider.get().manage().window().maximize();
            driverProvider.get().manage().deleteAllCookies();
            testStartTime = System.currentTimeMillis();
        }

        @AfterScenario(uponOutcome = Outcome.FAILURE)
        public void afterFailureScenario() {
            LOGGER.error("Wystapil blad w tescie.");
        }

        @AfterStory
        public void afterStory() {
            LOGGER.info("Czas wykonania calego testu : " + (System.currentTimeMillis() - testStartTime) / 1000 + " sek.");

            getMainFrame();
            closeModalWindow();
            clickLogout();
            closeBrowser();
        }

        private void closeBrowser() {
            try {
                driverProvider.end();
            } catch (WebDriverException e) {
                try {
                    driverProvider.get().close();
                } catch (WebDriverException e1) {
                    try {
                        driverProvider.get().quit();
                    } catch (WebDriverException e2) {
                        LOGGER.warn("Wystapil blad przy zamykaniu przegladarki po zakonczonych testach.");
                    }
                }
            }
        }

        private void clickLogout() {
            try {
                driverProvider.get().findElement(By.id("MOD_DSH_HDR_logout")).click();
            } catch (WebDriverException e) {
                LOGGER.info("Wystapil blad przy kliknieciu w przycisk 'Wyloguj'.");
            }
        }

        private void closeModalWindow() {
            try {
                driverProvider.get().findElement(By.id("MOD_TRN_DET_close")).click();
                driverProvider.get().findElement(By.id("MOD_TRN_DET_close_yes")).click();
            } catch (NoSuchElementException e) {
                LOGGER.info("Nie znaleziono przycisku do zamkniecia okna modalnego.");
            }
        }

        private void getMainFrame() {
            driverProvider.get().switchTo().defaultContent();
            driverProvider.get().switchTo().frame("main");
        }
    }