package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private final By SHOPPING_CART_BTN_ELEMENT = By.xpath("//a[@class='shopping_cart_link']");
    private final By TITLE_ELEMENT = By.xpath("//span[@class='title']");
    private final By SORT_DROPDOWN = By.xpath("//select[@class='product_sort_container']");
    private final By INVENTORY_ITEM_NAMES = By.xpath("//div[@class='inventory_item_name']");
    private final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private final By EXPAND_LEFT_SIDE_MENU = By.id("react-burger-menu-btn");
    private final By SHOPPING_CART_ICON = By.xpath("//span[@class='shopping_cart_badge']");

    private static final String ITEM_PRICE_ELEMENT = "//div[text()='%s']/ancestor::div[@class='inventory_item']" +
            "//div[@class='inventory_item_price']";
    private static final String ADD_TO_CART_BTN_ELEMENT = "//div[text()='%s']/ancestor::div[@class='inventory_item']" +
            "//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice(String productName) {
        By fullLocator = By.xpath(String.format(ITEM_PRICE_ELEMENT, productName));
        return driver.findElement(fullLocator).getText();
    }

    public List<String> getProductsPrices(List<String> products) {
        List<WebElement> elements = driver.findElements(INVENTORY_ITEM_NAMES);
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

    public boolean isLogoutButtonDisplayed() {
        expandLeftSideMenu();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_LINK));
        return driver.findElement(LOGOUT_LINK).isDisplayed();
    }

    public CartPage openProductCart() {
        driver.findElement(SHOPPING_CART_BTN_ELEMENT).click();
        return new CartPage(driver);
    }

    public ProductsPage addProductToCart(String productName) {
        By fullLocator = By.xpath(String.format(ADD_TO_CART_BTN_ELEMENT, productName));
        driver.findElement(fullLocator).click();
        return this;
    }

    public ProductsPage addProductsToCart(List<String> productsToAdd) {
        List<WebElement> products = driver.findElements(INVENTORY_ITEM_NAMES);
        for (WebElement product : products) {
            for (String s : productsToAdd) {
                if (product.getText().equals(s)) {
                    driver.findElement(By.xpath(String.format(ADD_TO_CART_BTN_ELEMENT, s))).click();
                }
            }
        }
        driver.findElement(SHOPPING_CART_BTN_ELEMENT).click();
        return this;
    }

    public ProductsPage clickAddToCartForAllProducts() {
        List<WebElement> products = driver.findElements(By.xpath("//button[text()='Add to cart']"));
        products.forEach(WebElement::click);
        return this;
    }

    public int getAmountOfAddedProductsDisplayedInCartIcon() {
        String amount = driver.findElement(SHOPPING_CART_ICON).getText();
        return Integer.parseInt(amount);
    }

    public List<WebElement> getAllProducts() {
        return driver.findElements(By.xpath("//div[@class='inventory_item']"));
    }

    public String getTitle() {
        return driver.findElement(TITLE_ELEMENT).getText();
    }

    public LeftSideMenu expandLeftSideMenu() {
        driver.findElement(EXPAND_LEFT_SIDE_MENU).click();
        return new LeftSideMenu(driver);
    }

    public List<String> sortProductsNamesAndGetList(ProductsSortOption sortOption) {
        Select select = new Select(driver.findElement(SORT_DROPDOWN));
        select.selectByVisibleText(sortOption.getOption());
        return driver.findElements(INVENTORY_ITEM_NAMES).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
