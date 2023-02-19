package com.saucedemo.test;

import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsTest extends BaseTest {

    @Test
    public void checkThatCartIconDisplayAmountOfAddedProducts() {
        ProductsPage productsPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .clickAddToCartForAllProducts();
        List<WebElement> allProducts = productsPage.getAllProducts();
        Assertions.assertThat(productsPage.getAmountOfAddedProductsDisplayedInCartIcon())
                .isEqualTo(allProducts.size())
                .as("Icon should display value " + allProducts.size());
    }

    @Test
    public void checkProductsSorting() {
        List<String> expectedProducts = new ArrayList<>();
        expectedProducts.add("Sauce Labs Backpack");
        expectedProducts.add("Sauce Labs Bike Light");
        expectedProducts.add("Sauce Labs Bolt T-Shirt");
        expectedProducts.add("Sauce Labs Fleece Jacket");
        expectedProducts.add("Sauce Labs Onesie");
        expectedProducts.add("Test.allTheThings() T-Shirt (Red)");
        Collections.reverse(expectedProducts);
        List<String> actualSortedProducts = new LoginPage(getDriver())
                .loginAsStandardUser()
                .sortProductsNamesAndGetList("Z - A");
        Assertions.assertThat(actualSortedProducts)
                .isEqualTo(expectedProducts)
                .as("List should be in the following order " + expectedProducts);
        Collections.sort(expectedProducts);
        ProductsPage productsPage = new ProductsPage(getDriver());
        actualSortedProducts = productsPage.sortProductsNamesAndGetList("A - Z");
        Assertions.assertThat(actualSortedProducts)
                .isEqualTo(expectedProducts)
                .as("List should be in the following order " + expectedProducts);
    }
}
