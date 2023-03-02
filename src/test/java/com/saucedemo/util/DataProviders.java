package com.saucedemo.util;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "unsuccessfulLoginDataProvider")
    public static Object[][] unsuccessfulLoginDataProvider() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"test", "123", "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"},
                {"locked_out_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
        };
    }

    @DataProvider(name = "dataProviderForCheckOutForm")
    public static Object[][] dataProviderForCheckOutForm() {
        return new Object[][]{
                {"", "SomeLastName", "12345", "Error: First Name is required"},
                {"SomeFirstName", "", "12345", "Error: Last Name is required"},
                {"SomeFirstName", "SomeLastName", "", "Error: Postal Code is required"}
        };
    }
}
