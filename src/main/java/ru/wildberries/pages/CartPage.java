package ru.wildberries.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage<CartPage> {

    @FindBy(xpath = "//h1[@class='basket-section__header basket-section__header--main active']")
    private WebElement cartPageTitle; // локатор для альтернативной проверки, что мы находимся в корзине

    @FindBy(xpath = "//span[@class='good-info__good-name']")
    private WebElement cartItem;

    @FindBy(xpath = "//div[@class='accordion__goods-count']")
    private WebElement goodsCountLabel;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartItemText() {
        return cartItem.getText();
    }

    public String getPageTitle() {
        return cartPageTitle.getText();
    }
}
