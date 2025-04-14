package ru.wildberries.utils.configs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverConfig {
    private static final Logger LOGGER = LogManager.getLogger(WebDriverConfig.class);
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        LOGGER.info("Initializing ChromeDriver");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(AppConfig.getTimeout())
        );
        LOGGER.debug("ChromeDriver initialized with timeout: {} seconds", AppConfig.getTimeout());
    }

    public static void quitDriver() {
        if (driver != null) {
            LOGGER.info("Quitting driver");
            driver.quit();
            driver = null;
        }
    }
}
