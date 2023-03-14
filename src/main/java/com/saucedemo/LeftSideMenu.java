package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class LeftSideMenu extends BasePage {

    private final By MENU_ITEMS = By.xpath("//nav[@class='bm-item-list']/a");

    public LeftSideMenu(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllItems() {
        List<WebElement> items = driver.findElements(MENU_ITEMS);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MENU_ITEMS));
        return items.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
