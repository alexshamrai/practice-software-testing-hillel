package com.practicesoftwaretesting;

import com.codeborne.selenide.Configuration;
import com.practicesoftwaretesting.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    static ConfigReader configReader = new ConfigReader();

    static {
        Configuration.baseUrl = configReader.getProperty("base.url");
        Configuration.timeout = Long.parseLong(configReader.getProperty("timeout"));
        Configuration.browserSize = configReader.getProperty("browser.size");
        Configuration.clickViaJs = Boolean.parseBoolean(configReader.getProperty("click.via.js"));
        Configuration.fastSetValue = Boolean.parseBoolean(configReader.getProperty("fast.set.value"));
        Configuration.headless = Boolean.parseBoolean(configReader.getProperty("headless"));
        Configuration.proxyEnabled = Boolean.parseBoolean(configReader.getProperty("proxy.enabled"));
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
