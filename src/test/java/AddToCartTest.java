
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.wildberries.pages.ProductPage;
import ru.wildberries.utils.configs.AppConfig;
import ru.wildberries.utils.configs.WebDriverConfig;


public class AddToCartTest {
    private static final Logger LOGGER = LogManager.getLogger(AddToCartTest.class);
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        driver = WebDriverConfig.getDriver();
        driver.get(AppConfig.getProductUrl());
        LOGGER.debug("Opened URL: {}", AppConfig.getProductUrl());
    }

    @Test
    public void testAddToCart() {
        LOGGER.info("Starting testAddToCart");
        String productName = "Щелкунчик по балету Чайковского. Сказка для детей 3-6 лет";
        new ProductPage(driver)
                .verifyPageOpened()
                .addToCart()
                .verifyAddToCartButtonClicked()
                .verifyProductTitle(productName)
                .goToCart()
                .verifyCartPageUrl()
                .verifyProductInCart(productName)
                .verifyCartIsNotEmpty();
        LOGGER.info("Test completed successfully");
    }

    @AfterEach
    public void tearDown() {
        WebDriverConfig.quitDriver();
        LOGGER.info("Test tear down completed");
    }
}
