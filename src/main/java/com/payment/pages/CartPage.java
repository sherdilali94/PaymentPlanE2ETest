package com.payment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    private By checkoutButton = By.id("checkout");
    private By cartItem = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean containsBackpack() {
        return driver.findElements(cartItem).stream()
                .anyMatch(item ->
                        item.getText().equals("Sauce Labs Backpack"));
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }
}
