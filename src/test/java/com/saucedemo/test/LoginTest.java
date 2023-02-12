package com.saucedemo.test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void checkSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(getDriver());
        Assert.assertTrue(productPage.isLogoutButtonDisplayed());
    }

    @Test(dataProvider = "unsuccessfulLoginDataProvider")
    public void checkUnsuccessfulLogin(String login, String pass, String expectedMessage) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(login, pass);
        Assert.assertEquals(loginPage.getUnsuccessfulLoginMessage(), expectedMessage);
    }

    @DataProvider(name = "unsuccessfulLoginDataProvider")
    public Object[][] unsuccessfulLoginDataProvider() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"test", "123", "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"},
                {"locked_out_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
        };
    }

}
