package ru.wildberries.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartPage extends BasePage<CartPage> {

    @FindBy(xpath = "//h1[@class='basket-section__header basket-section__header--main active']")
    private WebElement cartPageTitle; // локатор для альтернативной проверки, что мы находимся в корзине

    @FindBy(xpath = "//span[@class='good-info__good-name']")
    private WebElement cartItem;

    @FindBy(xpath = "//div[@class='accordion__goods-count']")
    private WebElement goodsCountLabel;

    private static final String EXPECTED_CART_URL = "https://www.wildberries.ru/lk/basket";


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage verifyProductInCart(String productName) {
        logger.info("Verifying product is in cart");
        assertThat(cartItem.getText())
                .as("Product title should have the %s name".formatted(productName))
                .isEqualTo(productName);
        return this;
    }

    public CartPage verifyCartPageUrl() {
        logger.info("Verifying cart page URL");
        assertThat(driver.getCurrentUrl())
                .as("Current basket URL should be equals to %s".formatted(EXPECTED_CART_URL))
                .isEqualTo(EXPECTED_CART_URL);
        return this;
    }

    public CartPage verifyCartPageTitle() {
        logger.info("Verifying cart page title");
        assertThat(cartPageTitle.isDisplayed())
                .as("Cart title should be displayed on this page")
                .isTrue();
        return this;
    }

    public CartPage verifyCartIsNotEmpty() {
        logger.info("Verifying cart is not empty");
        assertThat(goodsCountLabel.isDisplayed())
                .as("Cart should contain at least one item")
                .isTrue();
        return this;
    }
}
