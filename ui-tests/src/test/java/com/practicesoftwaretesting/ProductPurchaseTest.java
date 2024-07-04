package com.practicesoftwaretesting;

import com.practicesoftwaretesting.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductPurchaseTest extends BaseTest {


    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    Header header = new Header();
    CheckoutPage checkoutPage = new CheckoutPage();

    @BeforeEach
    void setup() {
        registerAndLoginAsNewUser();
    }

    @Test
    void addProductToCartAndPurchaseIt() {
        homePage.isLoaded()
                .clickOnTheFirstProduct();

        productPage.isLoaded()
                .addToCart();

        header.clickCartMenuItem();
        checkoutPage.isLoaded()
                .proceedToCheckout()
                .proceedToCheckoutSignedIn()
                .proceedToCheckoutBillingAddress()
                .chooseCashPaymentMethodAndConfirm()
                .assertThat()
                .successfulMessageIsDisplayed();
    }
}
