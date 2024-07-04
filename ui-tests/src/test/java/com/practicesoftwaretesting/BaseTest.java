package com.practicesoftwaretesting;

import com.codeborne.selenide.Configuration;
import com.practicesoftwaretesting.pages.AccountPage;
import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.user.UserSteps;
import com.practicesoftwaretesting.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.practicesoftwaretesting.user.UserSteps.DEFAULT_PASSWORD;

public abstract class BaseTest {

    UserSteps userSteps = new UserSteps();
    LoginPage loginPage = new LoginPage();
    AccountPage accountPage = new AccountPage();
    HomePage homePage = new HomePage();

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

    public void registerAndLoginAsNewUser() {
        var email = userSteps.getUserEmail();
        userSteps.registerUser(email, DEFAULT_PASSWORD);

        loginPage.open()
                .isLoaded()
                .login(email, DEFAULT_PASSWORD);
        accountPage.isLoaded();
        homePage.open();
    }

    public void loginAsAdmin() {
        loginPage.open()
                .isLoaded()
            .login(UserSteps.ADMIN_EMAIL, UserSteps.ADMIN_PASSWORD);
        accountPage.isLoaded();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
