package com.saucedemo.test;

import com.saucedemo.page.CartPage;
import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void checkProductIsAddedToCart() {
        final String productName = "Sauce Labs Backpack";
        String expectedProductPrice = new LoginPage(getDriver())
                .loginAsStandardUser()
                .getProductPrice(productName);
        CartPage cartPage = new ProductsPage(getDriver())
                .addProductToCart(productName)
                .openProductCart();
        Assertions.assertThat(cartPage.getProductPrice(productName))
                .isEqualTo(expectedProductPrice)
                .as("Price should be " + expectedProductPrice);
    }

    @Test
    public void checkProductsAreAddedToCart() {
        final List<String> products = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket");
        List<String> expectedProductsPrices = new LoginPage(getDriver())
                .loginAsStandardUser()
                .getProductsPrices(products);
        CartPage cartPage = new ProductsPage(getDriver())
                .addProductsToCart(products)
                .openProductCart();
        Assertions.assertThat(cartPage.getProductsPrices(products))
                .isEqualTo(expectedProductsPrices)
                .as("Products in cart should have the following prices: " + expectedProductsPrices);
    }

    @Test
    public void checkRemoveProductsFromCart() {
        final List<String> products = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket");
        final List<String> expectedProductsAfterRemove = List.of("Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket");
        List<String> actualProductsAfterRemove = new LoginPage(getDriver())
                .loginAsStandardUser()
                .addProductsToCart(products)
                .openProductCart()
                .removeProductFromCart("Sauce Labs Bike Light")
                .getAllProductsNames();
        Assertions.assertThat(actualProductsAfterRemove)
                .isNotNull()
                .isEqualTo(expectedProductsAfterRemove)
                .as("Product 'Sauce Labs Bike Light' was not removed");
    }

    @Test
    public void checkContinueShopping() {
        final int expectedProductsAmount = 6;
        List<WebElement> productsAtProductsPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .openProductCart()
                .clickContinueShopping()
                .getAllProducts();
        Assertions.assertThat(productsAtProductsPage.size())
                .isEqualTo(expectedProductsAmount)
                .as("Product page should contain " + expectedProductsAmount);
    }
}
