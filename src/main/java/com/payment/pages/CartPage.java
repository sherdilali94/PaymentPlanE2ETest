package com.payment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private By checkoutButton = By.id("checkout");
    private By cartItem = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsBackpack() {
        try {
            return wait.until(d -> d.findElements(cartItem).stream()
                    .anyMatch(item -> item.getText().equals("Sauce Labs Backpack")));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
