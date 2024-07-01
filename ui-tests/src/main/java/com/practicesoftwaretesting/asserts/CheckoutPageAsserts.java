package com.practicesoftwaretesting.asserts;

import com.practicesoftwaretesting.pages.CheckoutPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPageAsserts extends CheckoutPage {

    public CheckoutPageAsserts successfulMessageIsDisplayed() {
        $(MESSAGE).shouldHave(text("Payment was successful"));
        return this;
    }
}
