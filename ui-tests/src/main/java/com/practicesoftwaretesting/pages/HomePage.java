package com.practicesoftwaretesting.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.className;

public class HomePage {

    private static final By BANNER = className("img-fluid");

    public HomePage isLoaded() {
        $(BANNER).shouldBe(visible);
        return this;
    }
}
