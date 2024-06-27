package com.practicesoftwaretesting;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FirstSelenideTest {

    @Test
    void userCanLoginByUsername() {
        open("https://practicesoftwaretesting.com/#/auth/login");
        $(byId("email")).setValue("customer@practicesoftwaretesting.com");
        $(byId("password")).setValue("welcome01");
        $(byClassName("btnSubmit")).click();
        $(byId("menu")).shouldHave(text("Jane Doe"));
        $(by("data-test", "page-title")).shouldHave(text("My account"));
    }
}
