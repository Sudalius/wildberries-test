package ru.wildberries.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductPage extends BasePage<ProductPage> {

    @FindBy(xpath = "//h1[@class='product-page__title']")
    private WebElement productTitle;

    @FindBy(xpath = "(//*[text()='Добавить в корзину'])[last()]")
    private WebElement addToCartButton;

    @FindBy(xpath = "(//a[text()='Перейти в корзину'])[last()]")
    private WebElement goToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToCart() {
        logger.info("Adding product to cart");
        addToCartButton.click();
        return this;
    }

    public CartPage goToCart() {
        logger.info("Navigating to cart");
        goToCartButton.click();
        return new CartPage(driver);
    }

    public ProductPage verifyPageOpened() {
        logger.debug("Verifying product page is opened");
        assertThat(productTitle.isDisplayed())
                .as("Product page should be displayed with visible product name")
                .isTrue();
        return this;
    }

    public ProductPage verifyProductTitle(String productName) {
        logger.debug("Verifying product title");
        assertThat(productTitle.getText())
                .as("Product title should have the %s name".formatted(productName))
                .isEqualTo(productName);
        return this;
    }

    public ProductPage verifyAddToCartButtonClicked() {
        logger.debug("Verifying addToCartButton has been clicked");
        assertThat(goToCartButton.isDisplayed())
                .as("GoToCartButton should be displayed")
                .isTrue();
        return this;
    }
}
