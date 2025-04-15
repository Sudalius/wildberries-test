package ru.wildberries.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public String getProductTitleText() {
        return productTitle.getText();
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public boolean isGoToCartButtonDisplayed() {
        return goToCartButton.isDisplayed();
    }

    public CartPage clickGoToCartButton() {
        goToCartButton.click();
        return new CartPage(driver);
    }
}
