package com.saucedemo;

import com.saucedemo.util.DataProviders;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void checkSuccessfulLogin() {
        ProductsPage productsPage = new LoginPage(getDriver())
                .open()
                .loginAsStandardUser();
        Assertions.assertThat(productsPage.isLogoutButtonDisplayed())
                .as("Logout btn should be displayed after successful login")
                .isTrue();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "unsuccessfulLoginDataProvider")
    @Severity(SeverityLevel.CRITICAL)
    public void checkUnsuccessfulLogin(String login, String pass, String expectedMessage) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(login, pass);
        Assertions.assertThat(loginPage.getUnsuccessfulLoginMessage())
                .as("Message: " + expectedMessage + " should be displayed")
                .isEqualTo(expectedMessage);
    }
}
