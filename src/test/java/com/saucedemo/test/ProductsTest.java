package com.saucedemo.test;

import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ProductsTest extends BaseTest {

    @Test
    public void checkThatCartIconDisplayAmountOfAddedProducts() {
        ProductsPage productsPage = new LoginPage(getDriver())
                .loginAsStandardUser()
                .clickAddToCartForAllProducts();
        List<WebElement> allProducts = productsPage.getAllProducts();
        Assertions.assertThat(productsPage.getAmountOfAddedProductsDisplayedInCartIcon())
                .as("Icon should display value " + allProducts.size())
                .isEqualTo(allProducts.size());
    }

    @Test
    public void checkProductsSorting() {
        List<String> actualSortedProducts = new LoginPage(getDriver())
                .loginAsStandardUser()
                .sortProductsNamesAndGetList("Z - A");
        Assertions.assertThat(actualSortedProducts)
                .isNotNull()
                .as("Products should be sorted in alphabetic order vice versa (from Z to A)")
                .isSortedAccordingTo(Comparator.reverseOrder());
        ProductsPage productsPage = new ProductsPage(getDriver());
        actualSortedProducts = productsPage.sortProductsNamesAndGetList("A - Z");
        Assertions.assertThat(actualSortedProducts)
                .isNotNull()
                .as("Products should be sorted in alphabetic order (from A to Z)")
                .isSorted();
    }
}
