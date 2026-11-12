package com.payment.tests;

import com.payment.pages.LoginPage;
import com.payment.pages.ProductsPage;
import com.payment.pages.CartPage;
import com.payment.pages.CheckoutPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentPlanE2ETest extends BaseTest {

    @Test
    public void completeCheckoutSuccessfully() {

        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password")
        );

        Assert.assertTrue(
                loginPage.urlVerify(),"Login failed"
        );

        // 2. Add product to cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();
        productsPage.openCart();

        // 3. Verify cart
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.containsBackpack(),
                "Product was not added to cart"
        );

        // 4. Proceed to checkout
        cartPage.checkout();

        // 5. Enter customer details
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCustomerDetails(
                "Test",
                "Customer",
                "12345"
        );

        checkoutPage.continueToOverview();

        Assert.assertTrue(
                checkoutPage.urlCheckoutVerify(),"Checkout overview not displayed"
        );

        // 6. Complete demo order
        checkoutPage.finishOrder();

        // 7. Verify confirmation
        Assert.assertTrue(
                        checkoutPage.urlCheckoutCompleteVerify(),
                "Order was not completed"
        );

        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!"
        );
    }
}
