package com.practicesoftwaretesting.pages;

import com.practicesoftwaretesting.asserts.HeaderAsserts;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.utils.SelectorUtils.byDataTest;

public class Header {

    private static final By SIGN_IN = byDataTest("nav-sign-in");
    protected static final By PROFILE_MENU = byDataTest("nav-menu");

    public void clickSignInMenuItem() {
        $(SIGN_IN).click();
    }

    public HeaderAsserts assertThat() {
        return new HeaderAsserts();
    }
}
