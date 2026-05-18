package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws Exception {

        String browser = System.getenv("BROWSER");
        String seleniumUrl = System.getenv("SELENIUM_URL");

        if (browser == null) {
            browser = "firefox";
        }

        if (seleniumUrl == null) {
            seleniumUrl = "http://firefox:4444/wd/hub";
        }

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                driver = new RemoteWebDriver(
                        new URL(seleniumUrl),
                        chromeOptions
                );

                break;

            case "firefox":

            default:

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");

                driver = new RemoteWebDriver(
                        new URL(seleniumUrl),
                        firefoxOptions
                );

                break;
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
