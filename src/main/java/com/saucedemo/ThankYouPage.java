package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ThankYouPage extends BasePage {

    private final By CHECKOUT_SUCCESSFUL_COMPLETE_MESSAGE = By.xpath("//h2");
    private final By BACK_HOME_BTN = By.id("back-to-products");

    public ThankYouPage(WebDriver driver) {
        super(driver);
    }

    public String getThankYouMessage() {
        return driver.findElement(CHECKOUT_SUCCESSFUL_COMPLETE_MESSAGE).getText();
    }

    public ProductsPage clickBackHome() {
        driver.findElement(BACK_HOME_BTN).click();
        return new ProductsPage(driver);
    }
}
