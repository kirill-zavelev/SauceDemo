package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By INVENTORY_ITEM_NAME_ELEMENT = By.xpath("//div[@class='inventory_item_name']");
    private final By CHECKOUT_BTN = By.id("checkout");
    private final By CONTINUE_SHOPPING_BTN = By.id("continue-shopping");
    private final By TITLE_ELEMENT = By.xpath("//span[@class='title']");

    private static final String CART_ITEM_PRICE_ELEMENT = "//div[text()='%s']/ancestor::div[@class='cart_item']" +
            "//div[@class='inventory_item_price']";
    private static final String REMOVE_BTN_ELEMENT = "//div[text()='%s']/ancestor::div[@class='cart_item']" +
            "//button[text()='Remove']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(CART_ITEM_PRICE_ELEMENT, productName))).getText();
    }

    public List<String> getProductsPrices(List<String> products) {
        List<WebElement> elements = driver.findElements(INVENTORY_ITEM_NAME_ELEMENT);
        List<String> prices = new ArrayList<>();

        for (WebElement element : elements) {
            for (String s : products) {
                if (element.getText().equals(s)) {
                    prices.add(getProductPrice(s));
                }
            }
        }
        return prices;
    }

    public CartPage removeProductFromCart(String productName) {
        By fullLocator = By.xpath(String.format(REMOVE_BTN_ELEMENT, productName));
        driver.findElement(fullLocator).click();
        return this;
    }

    public CheckOutYourInformationPage clickCheckOut() {
        driver.findElement(CHECKOUT_BTN).click();
        return new CheckOutYourInformationPage(driver);
    }

    public ProductsPage clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BTN).click();
        return new ProductsPage(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE_ELEMENT).getText();
    }
}
