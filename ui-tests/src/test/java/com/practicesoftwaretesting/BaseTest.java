package com.practicesoftwaretesting;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    static {
        Configuration.baseUrl = "https://practicesoftwaretesting.com/";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
