package com.practicesoftwaretesting.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.utils.SelectorUtils.byDataTest;

public class AccountPage {

    private static final By PAGE_TITLE = byDataTest("page-title");

    public AccountPage isLoaded() {
        $(PAGE_TITLE).shouldHave(text("My Account"));
        return this;
    }
}
