package com.saucedemo.test;

import com.saucedemo.page.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class LeftSideMenuTest extends BaseTest {

    @Test
    public void checkLeftSideMenuItems() {
        final List<String> expectedLeftSideMenuItems = List.of("ALL ITEMS", "ABOUT", "LOGOUT", "RESET APP STATE");
        List<String> actualLeftSideMenuItems = new LoginPage(getDriver())
                .loginAsStandardUser()
                .expandLeftSideMenu()
                .getAllItems();
        Assertions.assertThat(actualLeftSideMenuItems)
                .isEqualTo(expectedLeftSideMenuItems)
                .as("List of menu items should be: " + expectedLeftSideMenuItems);
    }
}
