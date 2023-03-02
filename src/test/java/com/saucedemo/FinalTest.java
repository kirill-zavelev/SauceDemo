package com.saucedemo;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FinalTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.MINOR)
    public void checkThankYouPageAndReturnBackHome() {
        final String product = "Sauce Labs Onesie";
        final String expectedThankYouPageMessage = "Thank you for your order!";
        final String expectedTitleAtProductsPage = "Products";
        CheckOutYourInformationPage checkOutYourInformationPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductToCart(product)
                .openProductCart()
                .clickCheckOut()
                .fillCheckoutForm("Name", "LastName", "12345");
        checkOutYourInformationPage.clickContinue();
        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(getDriver());
        String actualThankYouPageMessage = checkOutOverviewPage.clickFinish().getThankYouMessage();
        Assertions.assertThat(actualThankYouPageMessage)
                .as("Message should be " + expectedThankYouPageMessage)
                .isEqualTo(expectedThankYouPageMessage);
        ThankYouPage thankYouPage = new ThankYouPage(getDriver());
        String actualProductsPageTitle = thankYouPage.clickBackHome().getTitle();
        Assertions.assertThat(actualProductsPageTitle)
                .as("Title should be " + expectedTitleAtProductsPage)
                .isEqualTo(expectedTitleAtProductsPage);
    }
}
