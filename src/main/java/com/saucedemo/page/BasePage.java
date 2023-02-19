package com.saucedemo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BasePage {

    private final By INVENTORY_ITEM_NAME_ELEMENT = By.xpath("//div[@class='inventory_item_name']");
    private final By INVENTORY_ITEM_PRICE_ELEMENT = By.xpath("//div[@class='inventory_item_price']");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<String> getAllProductsNames() {
        List<WebElement> productsElements = driver.findElements(INVENTORY_ITEM_NAME_ELEMENT);
        return productsElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAllProductsPrices() {
        List<WebElement> productsElements = driver.findElements(INVENTORY_ITEM_PRICE_ELEMENT);
        return productsElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
