package com.saucedemo.page;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    private static final String ITEM_PRICE_ELEMENT = "//div[text()='%s']/ancestor::div[@class='inventory_item']" +
    "//div[@class='inventory_item_price']";
    private static final String ADD_TO_CART_BTN_ELEMENT = "//div[text()='%s']/ancestor::div[@class='inventory_item']" +
    "//button[text()='Add to cart']";
    private static final String SHOPPING_CART_BTN_ELEMENT = "//a[@class='shopping_cart_link']";
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(ITEM_PRICE_ELEMENT, productName))).getText();
    }

    public List<String> getProductsPrices(List<String> products) {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        List<String> prices = new ArrayList<>();

        for (WebElement element : elements) {
            for (String s : products) {
                if (element.getText().equals(s)) {
                    prices.add(driver.findElement(By.xpath(String.format(ITEM_PRICE_ELEMENT, s))).getText());
                }
            }
        }
        return prices;
    }

    public boolean isLogoutButtonDisplayed() {
        String logoutLinkElement = "logout_sidebar_link";
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(logoutLinkElement)));
        return driver.findElement(By.id(logoutLinkElement)).isDisplayed();
    }

    public CartPage addProductToCart(String productName) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_BTN_ELEMENT, productName))).click();
        driver.findElement(By.xpath(SHOPPING_CART_BTN_ELEMENT)).click();
        return new CartPage(driver);
    }

    public CartPage addProductsToCart(List<String> productsToAdd) {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        for (WebElement product : products) {
            for (String s : productsToAdd) {
                if (product.getText().equals(s)) {
                    driver.findElement(By.xpath(String.format(ADD_TO_CART_BTN_ELEMENT, s))).click();
                }
            }
        }
        driver.findElement(By.xpath(SHOPPING_CART_BTN_ELEMENT)).click();
        return new CartPage(driver);
    }
}
