package com.practicesoftwaretesting;

import com.practicesoftwaretesting.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.practicesoftwaretesting.user.UserSteps.getUserEmail;

public class ProductPurchaseTest extends BaseTest {

    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    Header header = new Header();
    CheckoutPage checkoutPage = new CheckoutPage();

    private String userId;

    @BeforeEach
    void setup() {
        var email = getUserEmail();
        userId = registerUser(email);
        loginUser(email, defaultPassword);
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

    @AfterEach
    void cleanup() {
        deleteUser(userId);
    }
}
