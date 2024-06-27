package com.practicesoftwaretesting.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.utils.SelectorUtils.byDataTest;

public class Header {

    public void clickSignInMenuItem() {
        $(byDataTest("nav-sign-in")).click();
    }
}
