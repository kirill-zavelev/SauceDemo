package com.saucedemo.test;

import com.saucedemo.page.CheckOutOverviewPage;
import com.saucedemo.page.CheckOutYourInformationPage;
import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ThankYouPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FinalTest extends BaseTest {

    @Test
    public void checkThankYouPageAndReturnBackHome() {
        final String product = "Sauce Labs Onesie";
        final String expectedThankYouPageMessage = "THANK YOU FOR YOUR ORDER";
        final String expectedTitleAtProductsPage = "PRODUCTS";
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
                .isEqualTo(expectedThankYouPageMessage)
                .as("Message should be " + expectedThankYouPageMessage);
        ThankYouPage thankYouPage = new ThankYouPage(getDriver());
        String actualProductsPageTitle = thankYouPage.clickBackHome().getTitle();
        Assertions.assertThat(actualProductsPageTitle)
                .isEqualTo(expectedTitleAtProductsPage)
                .as("Title should be " + expectedTitleAtProductsPage);
    }
}
