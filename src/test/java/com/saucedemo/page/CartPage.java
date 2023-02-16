package com.saucedemo.page;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private static final String INVENTORY_ITEM_NAME_ELEMENT = "//div[@class='inventory_item_name']";
    private static final String CART_ITEM_PRICE_ELEMENT = "//div[text()='%s']/ancestor::div[@class='cart_item']" +
            "//div[@class='inventory_item_price']";
    private static final String REMOVE_BTN_ELEMENT = "//div[text()='%s']/ancestor::div[@class='cart_item']" +
            "//button[text()='Remove']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(By.xpath(INVENTORY_ITEM_NAME_ELEMENT)).getText();
    }

    public List<String> getAllProductNames() {
        List<WebElement> productsNamesElements = driver.findElements(By.xpath(INVENTORY_ITEM_NAME_ELEMENT));
        List<String> productNames = new ArrayList<>();
        for (WebElement productNameElement : productsNamesElements) {
            productNames.add(productNameElement.getText());
        }
        return productNames;
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(CART_ITEM_PRICE_ELEMENT, productName))).getText();
    }

    public List<String> getProductsPrices(List<String> products) {
        List<WebElement> elements = driver.findElements(By.xpath(INVENTORY_ITEM_NAME_ELEMENT));
        List<String> prices = new ArrayList<>();

        for (WebElement element : elements) {
            for (String s : products) {
                if (element.getText().equals(s)) {
                    prices.add(driver.findElement(By.xpath(String.format(CART_ITEM_PRICE_ELEMENT, s))).getText());
                }
            }
        }
        return prices;
    }

    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BTN_ELEMENT, productName))).click();
    }
}
