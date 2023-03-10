package com.saucedemo;

import com.saucedemo.util.DataProviders;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class CheckOutTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void checkCheckoutFormWithValidData() {
        String product = getProductOrDefault(System.getProperty("product"));
        final String expectedTitle = "Checkout: Overview";
        CheckOutYourInformationPage checkOutYourInformationPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductToCart(product)
                .openProductCart()
                .clickCheckOut()
                .fillCheckoutForm("Name", "LastName", "12345");
        checkOutYourInformationPage.clickContinue();
        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(getDriver());
        String actualCheckOutOverviewTitle = checkOutOverviewPage.getCheckoutOverviewTitle();
        Assertions.assertThat(actualCheckOutOverviewTitle)
                .isEqualTo(expectedTitle)
                .as("Title text should be " + expectedTitle);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderForCheckOutForm")
    @Severity(SeverityLevel.MINOR)
    public void checkCheckoutFormWithInvalidData(String firstName, String lastName, String postalCode,
                                                 String errorMessage) {
        final String product = "Sauce Labs Onesie";
        CheckOutYourInformationPage checkOutYourInformationPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductToCart(product)
                .openProductCart()
                .clickCheckOut()
                .fillCheckoutForm(firstName, lastName, postalCode);
        checkOutYourInformationPage.clickContinue();
        String actualErrorText = checkOutYourInformationPage.getErrorText();
        Assertions.assertThat(actualErrorText)
                .as("Title text should be " + errorMessage)
                .isEqualTo(errorMessage);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    public void checkReturnToCartPage() {
        final String product = "Sauce Labs Onesie";
        final String expectedTitle = "Your Cart";
        String actualTitle = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductToCart(product)
                .openProductCart()
                .clickCheckOut()
                .clickCancel()
                .getTitle();
        Assertions.assertThat(actualTitle)
                .as("Title should be " + expectedTitle)
                .isEqualTo(expectedTitle);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    public void checkReturnToProductsPage() {
        final String product = "Sauce Labs Onesie";
        final String expectedTitle = "Products";
        CheckOutYourInformationPage checkOutYourInformationPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductToCart(product)
                .openProductCart()
                .clickCheckOut()
                .fillCheckoutForm("Name", "LastName", "12345");
        checkOutYourInformationPage.clickContinue();
        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(getDriver());
        Assertions.assertThat(checkOutOverviewPage.clickCancel().getTitle())
                .as("Title should be " + expectedTitle)
                .isEqualTo(expectedTitle);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void checkAddedProductsAreDisplayedInCheckout() {
        final List<String> products = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
        CartPage cartPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductsToCart(products)
                .openProductCart();
        List<String> expectedProductsPrices = cartPage.getAllProductsPrices();
        List<String> expectedProductsNames = cartPage.getAllProductsNames();
        cartPage.clickCheckOut()
                .fillCheckoutForm("Name", "LastName", "12345")
                .clickContinue();
        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(getDriver());
        List<String> actualProductsNames = checkOutOverviewPage.getAllProductsNames();
        List<String> actualProductsPrices = checkOutOverviewPage.getAllProductsPrices();
        Assertions.assertThat(actualProductsPrices)
                .as("Prices should be " + expectedProductsPrices)
                .isEqualTo(expectedProductsPrices);
        Assertions.assertThat(actualProductsNames)
                .as("Names should be " + expectedProductsNames)
                .isEqualTo(expectedProductsNames);
    }

    private String getProductOrDefault(String product) {
        if (product != null) {
            product = System.getProperty("product");
        } else {
            product = "Sauce Labs Bolt T-Shirt";
        }
        return product;
    }
}
