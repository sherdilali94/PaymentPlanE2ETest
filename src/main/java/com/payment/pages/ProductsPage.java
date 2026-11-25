package com.payment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private By backpack =
            By.id("add-to-cart-sauce-labs-backpack");

    private By cart =
            By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpack)).click();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
    }
}
