package com.saucedemo;

import com.saucedemo.util.PropertiesLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginPage extends BasePage {

    private final By USER_NAME = By.id("user-name");
    private final By PASSWORD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR = By.xpath("//h3[@data-test='error']");

    private Properties properties;

    public LoginPage(WebDriver driver) {
        super(driver);
        properties = PropertiesLoader.loadProperties();
    }

    public LoginPage open() {
        driver.get(properties.getProperty("base.url"));
        return this;
    }

    public ProductsPage loginAsStandardUser() {
        loginAs(properties.getProperty("standard.username"), properties.getProperty("standard.password"));
        return new ProductsPage(driver);
    }

    @Step("Login with username: {userName} and password: {password}")
    public void loginAs(String userName, String password) {
        driver.findElement(USER_NAME).sendKeys(userName);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getUnsuccessfulLoginMessage() {
        return driver.findElement(ERROR).getText();
    }
}
