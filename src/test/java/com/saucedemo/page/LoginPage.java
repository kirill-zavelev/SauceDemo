package com.saucedemo.page;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String password) {
        driver.findElement(By.id("user-name")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    public String getUnsuccessfulLoginMessage() {
        return driver.findElement(By.xpath("//h3")).getText();
    }
}
