package com.saucedemo.test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.page.CartPage;
import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void checkProductIsAddedToCart() {
        final String productName = "Sauce Labs Backpack";
        loginToTheApplication();
        ProductPage productPage = new ProductPage(getDriver());
        String expectedPrice = productPage.getProductPrice(productName);
        productPage.addProductToCart(productName);
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), productName,
                "Name of product should be " + productName);
        Assert.assertEquals(cartPage.getProductPrice(productName), expectedPrice,
                "Name of product should be " + expectedPrice);
    }

    @Test
    public void checkProductsAreAddedToCart() {
        loginToTheApplication();
        ProductPage productPage = new ProductPage(getDriver());
        List<String> products = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket");
        List<String> expectedProductPrices = productPage.getProductsPrices(products);
        productPage.addProductsToCart(products);
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductsPrices(products), expectedProductPrices,
                "Prices of the products are not equal");
        Assert.assertEquals(cartPage.getAllProductNames(), products,
                "Names of the products are not equal");
    }

    @Test
    public void checkRemoveProductFromCart() {
        loginToTheApplication();
        ProductPage productPage = new ProductPage(getDriver());
        List<String> products = List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket");
        productPage.addProductsToCart(products);
        CartPage cartPage = new CartPage(getDriver());
        cartPage.removeProductFromCart("Sauce Labs Bike Light");
        List<String> expectedProductsAfterRemove = List.of("Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket");
        Assert.assertEquals(cartPage.getAllProductNames(), expectedProductsAfterRemove,
                "Some product was not deleted");
    }

    private void loginToTheApplication() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");
    }
}
