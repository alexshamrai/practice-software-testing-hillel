package com.practicesoftwaretesting.pages;

import com.practicesoftwaretesting.asserts.RegisterPageAsserts;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    public RegisterPage isLoaded() {
        $("h3").shouldHave(text("Customer registration"));
        return this;
    }

    public RegisterPageAsserts assertThat() {
        return new RegisterPageAsserts();
    }

}
