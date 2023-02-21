package com.saucedemo.page;

public enum ProductsSortOption {

    NameAtoZ("Name (A to Z)"),
    NameZtoA("Name (Z to A)");

    private final String option;

    ProductsSortOption(String item) {
        this.option = item;
    }

    public String getOption() {
        return option;
    }
}
