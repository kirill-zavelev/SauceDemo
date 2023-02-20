package com.saucedemo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutYourInformationPage extends BasePage {

    private final By FIRST_NAME = By.id("first-name");
    private final By LAST_NAME = By.id("last-name");
    private final By POSTAL_CODE = By.id("postal-code");
    private final By CONTINUE_BTN = By.id("continue");
    private final By CANCEL_BTN = By.id("cancel");
    private final By ERROR_MESSAGE_ELEMENT = By.xpath("//h3");

    public CheckOutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutYourInformationPage fillCheckoutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        return this;
    }

    public CartPage clickCancel() {
        driver.findElement(CANCEL_BTN).click();
        return new CartPage(driver);
    }

    public CheckOutOverviewPage clickContinue() {
        driver.findElement(CONTINUE_BTN).click();
        return new CheckOutOverviewPage(driver);
    }

    public String getErrorText() {
        return driver.findElement(ERROR_MESSAGE_ELEMENT).getText();
    }
}
