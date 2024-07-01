package com.practicesoftwaretesting.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.utils.SelectorUtils.byDataTest;

public class LoginPage {

    private static final By EMAIL_INPUT = byDataTest("email");
    private static final By PASSWORD_INPUT = byDataTest("password");
    private static final By LOGIN_BUTTON = byDataTest("login-submit");
    private static final By REGISTER_LINK = byDataTest("register-link");

    public LoginPage isLoaded() {
        $("h3").shouldHave(text("Login"));
        return this;
    }

    public void clickRegisterYourAccount() {
        $(REGISTER_LINK).click();
    }

    public void login(String email, String password) {
        $(EMAIL_INPUT).setValue(email);
        $(PASSWORD_INPUT).setValue(password);
        $(LOGIN_BUTTON).click();
    }
}
