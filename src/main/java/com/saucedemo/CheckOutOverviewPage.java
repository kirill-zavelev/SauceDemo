package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutOverviewPage extends BasePage {

    private final By TITLE_ELEMENT = By.xpath("//span[@class='title']");
    private final By CANCEL_BTN = By.id("cancel");
    private final By FINISH_BTN = By.id("finish");

    public CheckOutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutOverviewTitle() {
        return driver.findElement(TITLE_ELEMENT).getText();
    }

    public ProductsPage clickCancel() {
        driver.findElement(CANCEL_BTN).click();
        return new ProductsPage(driver);
    }

    public ThankYouPage clickFinish() {
        driver.findElement(FINISH_BTN).click();
        return new ThankYouPage(driver);
    }
}
