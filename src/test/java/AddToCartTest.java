
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.wildberries.pages.CartPage;
import ru.wildberries.pages.ProductPage;
import ru.wildberries.utils.configs.AppConfig;
import ru.wildberries.utils.configs.WebDriverConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class AddToCartTest {
    private static final Logger LOGGER = LogManager.getLogger(AddToCartTest.class);
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Initializing test setup");
        driver = WebDriverConfig.getDriver();
        driver.get(AppConfig.getProductUrl());
        LOGGER.debug("Opened product URL: {}", AppConfig.getProductUrl());
    }

    @Test
    public void testAddToCart() {
        LOGGER.info("Starting add to cart test");
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        String productName = "Щелкунчик по балету Чайковского. Сказка для детей 3-6 лет";

        assertThat(productPage.getProductTitleText())
                .as("Product page should contains this product title: %s".formatted(productName))
                .isEqualTo(productName);

        productPage.clickAddToCartButton();

        assertThat(productPage.isGoToCartButtonDisplayed())
                .as("'Go to cart' button should appear after adding product")
                .isTrue();

        productPage.clickGoToCartButton();

        assertThat(cartPage.getPageTitle())
                .as("Page title should be equals to 'Корзина'")
                .isEqualTo("Корзина");

        assertThat(cartPage.getCartItemText())
                .as("Product name in cart should match")
                .isEqualTo(productName);

        LOGGER.info("Add to cart test completed successfully");
    }

    @AfterEach
    public void tearDown() {
        WebDriverConfig.quitDriver();
        LOGGER.info("Test cleanup completed");
    }
}
