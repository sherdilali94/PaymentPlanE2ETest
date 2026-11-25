package com.payment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterCustomerDetails(
            String first, String last, String zip) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(first);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(last);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(zip);
    }

    public void continueToOverview() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    public boolean urlCheckoutVerify() {
        try {
            return wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean urlCheckoutCompleteVerify() {
        try {
            return wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
