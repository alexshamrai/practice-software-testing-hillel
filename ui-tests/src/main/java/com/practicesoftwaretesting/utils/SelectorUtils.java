package com.practicesoftwaretesting.utils;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.by;

public class SelectorUtils {

    public static By byDataTest(String attributeValue) {
        return by("data-test", attributeValue);
    }

    public static By byFor(String attributeValue) {
        return by("for", attributeValue);
    }
}
