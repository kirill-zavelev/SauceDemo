package com.saucedemo;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ProductsTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.MINOR)
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
    @Severity(SeverityLevel.MINOR)
    public void checkProductsSorting() {
        List<String> actualSortedProducts = new LoginPage(getDriver())
                .loginAsStandardUser()
                .sortProductsNamesAndGetList(ProductsSortOption.NameZtoA);
        Assertions.assertThat(actualSortedProducts)
                .isNotNull()
                .as("Products should be sorted in alphabetic order vice versa (from Z to A)")
                .isSortedAccordingTo(Comparator.reverseOrder());
        ProductsPage productsPage = new ProductsPage(getDriver());
        actualSortedProducts = productsPage.sortProductsNamesAndGetList(ProductsSortOption.NameAtoZ);
        Assertions.assertThat(actualSortedProducts)
                .isNotNull()
                .as("Products should be sorted in alphabetic order (from A to Z)")
                .isSorted();
    }
}
