package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;

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

                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");

                driver = new RemoteWebDriver(
                        new URL(seleniumUrl),
                        chromeOptions
                );

                break;

            case "firefox":
            default:

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");

                driver = new RemoteWebDriver(
                        new URL(seleniumUrl),
                        firefoxOptions
                );

                break;
        }

        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        Thread.sleep(2000);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
