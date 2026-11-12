package com.payment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private WebDriver driver;

    private By backpack =
            By.id("add-to-cart-sauce-labs-backpack");

    private By cart =
            By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        driver.findElement(backpack).click();
    }

    public void openCart() {
        driver.findElement(cart).click();
    }
}
