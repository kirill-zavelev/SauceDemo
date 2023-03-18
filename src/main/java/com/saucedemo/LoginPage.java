package com.saucedemo;

import com.saucedemo.util.PropertiesLoader;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

@Log4j2
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
        String baseUrl = properties.getProperty("base.url");
        driver.get(baseUrl);
        log.info("The following url: {} is opened", baseUrl);
        return this;
    }

    public ProductsPage loginAsStandardUser() {
        String standardUsername = properties.getProperty("standard.username");
        String standardPassword = properties.getProperty("standard.password");
        loginAs(standardUsername, standardPassword);
        log.info("Standard User: username - {}, password - {} was logged in", standardUsername, standardPassword);
        return new ProductsPage(driver);
    }

    @Step("Login with username: {userName} and password: {password}")
    public void loginAs(String userName, String password) {
        driver.findElement(USER_NAME).sendKeys(userName);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        log.info("The User: username - {}, password - {} was logged in", userName, password);
    }

    public String getUnsuccessfulLoginMessage() {
        return driver.findElement(ERROR).getText();
    }
}
