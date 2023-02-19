package com.saucedemo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By USER_NAME = By.id("user-name");
    private final By PASSWORD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR = By.xpath("//h3[@data-test='error']");

    private static final String STANDARD_USER = "standard_user";
    private static final String STANDARD_PASSWORD = "secret_sauce";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public ProductsPage loginAsStandardUser() {
        loginAs(STANDARD_USER, STANDARD_PASSWORD);
        return new ProductsPage(driver);
    }

    public void loginAs(String userName, String password) {
        driver.findElement(USER_NAME).sendKeys(userName);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getUnsuccessfulLoginMessage() {
        return driver.findElement(ERROR).getText();
    }
}
