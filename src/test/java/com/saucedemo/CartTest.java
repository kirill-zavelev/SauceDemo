package com.saucedemo;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    private static final List<String> PRODUCTS = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket");
    private static final int EXPECTED_PRODUCTS_AMOUNT = 6;


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test checks that Product is added to the Cart and validates it's name and price")
    public void checkProductIsAddedToCart() {
        final String productName = "Sauce Labs Backpack";
        String expectedProductPrice = new LoginPage(getDriver())
                .loginAsStandardUser()
                .getProductPrice(productName);
        CartPage cartPage = new ProductsPage(getDriver())
                .addProductToCart(productName)
                .openProductCart();
        Assertions.assertThat(cartPage.getProductPrice(productName))
                .as("Price should be " + expectedProductPrice)
                .isEqualTo(expectedProductPrice);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test checks that Products are added to the Cart and validates their prices")
    public void checkProductsAreAddedToCart() {
        List<String> expectedProductsPrices = new LoginPage(getDriver())
                .loginAsStandardUser()
                .getProductsPrices(PRODUCTS);
        CartPage cartPage = new ProductsPage(getDriver())
                .addProductsToCart(PRODUCTS)
                .openProductCart();
        Assertions.assertThat(cartPage.getProductsPrices(PRODUCTS))
                .as("Products in cart should have the following prices: " + expectedProductsPrices)
                .isEqualTo(expectedProductsPrices);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test checks that Products are removed from the Cart")
    public void checkRemoveProductsFromCart() {
        final List<String> expectedProductsAfterRemove = List.of("Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket");
        List<String> actualProductsAfterRemove = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductsToCart(PRODUCTS)
                .openProductCart()
                .removeProductFromCart("Sauce Labs Bike Light")
                .getAllProductsNames();
        Assertions.assertThat(actualProductsAfterRemove)
                .isNotNull()
                .as("Product 'Sauce Labs Bike Light' was not removed")
                .isEqualTo(expectedProductsAfterRemove);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Test checks that 'Continue Shopping' button leads to Products Page")
    public void checkContinueShopping() {
        List<WebElement> productsAtProductsPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .openProductCart()
                .clickContinueShopping()
                .getAllProducts();
        Assertions.assertThat(productsAtProductsPage.size())
                .as("Product page should contain " + EXPECTED_PRODUCTS_AMOUNT)
                .isEqualTo(EXPECTED_PRODUCTS_AMOUNT);
    }
}
