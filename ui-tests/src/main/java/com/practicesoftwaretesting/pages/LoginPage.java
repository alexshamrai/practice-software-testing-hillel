package com.practicesoftwaretesting.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public LoginPage isLoaded() {
        $("h3").shouldHave(text("Login"));
        return this;
    }

    public void clickRegisterYourAccount() {
        $(by("data-test", "register-link")).click();
    }
}
