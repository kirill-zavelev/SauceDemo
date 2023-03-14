package com.saucedemo;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

public class LeftSideMenuTest extends BaseTest {

    final List<String> LEFT_SIDE_MENU_ITEMS = List.of("All Items", "About", "Logout", "Reset App State");

    @Test
    @Severity(SeverityLevel.MINOR)
    public void checkLeftSideMenuItems() {
        List<String> actualLeftSideMenuItems = new LoginPage(getDriver())
                .loginAsStandardUser()
                .expandLeftSideMenu()
                .getAllItems();
        Assertions.assertThat(actualLeftSideMenuItems)
                .as("List of menu items should be: " + LEFT_SIDE_MENU_ITEMS)
                .isEqualTo(LEFT_SIDE_MENU_ITEMS);
    }
}
