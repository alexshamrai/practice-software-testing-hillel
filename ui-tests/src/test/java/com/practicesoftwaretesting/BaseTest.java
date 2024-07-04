package com.practicesoftwaretesting;

import com.codeborne.selenide.Configuration;
import com.practicesoftwaretesting.pages.AccountPage;
import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.user.UserSteps;
import com.practicesoftwaretesting.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    static ConfigReader configReader = new ConfigReader();
    private static final String adminEmail = configReader.getProperty("admin.email");
    private static final String adminPassword = configReader.getProperty("admin.password");
    private static final String defaultPassword = configReader.getProperty("default.password");

    static {
        Configuration.baseUrl = configReader.getProperty("base.url");
        Configuration.timeout = Long.parseLong(configReader.getProperty("timeout"));
        Configuration.browserSize = configReader.getProperty("browser.size");
        Configuration.clickViaJs = Boolean.parseBoolean(configReader.getProperty("click.via.js"));
        Configuration.fastSetValue = Boolean.parseBoolean(configReader.getProperty("fast.set.value"));
        Configuration.headless = Boolean.parseBoolean(configReader.getProperty("headless"));
        Configuration.proxyEnabled = Boolean.parseBoolean(configReader.getProperty("proxy.enabled"));
    }

    UserSteps userSteps = new UserSteps();
    LoginPage loginPage = new LoginPage();
    AccountPage accountPage = new AccountPage();
    HomePage homePage = new HomePage();

    public void registerAndLoginAsNewUser() {
        var email = userSteps.getUserEmail();
        userSteps.registerUser(email, defaultPassword);

        loginPage.open()
                .isLoaded()
                .login(email, defaultPassword);
        accountPage.isLoaded();
        homePage.open();
    }

    public void loginAsAdmin() {
        loginPage.open()
                .isLoaded()
                .login(adminEmail, adminPassword);
        accountPage.isLoaded();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
