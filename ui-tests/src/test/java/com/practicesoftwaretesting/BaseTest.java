package com.practicesoftwaretesting;

import com.codeborne.selenide.Configuration;
import com.practicesoftwaretesting.pages.AccountPage;
import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.user.UserSteps;
import com.practicesoftwaretesting.user.model.UserSearch;
import com.practicesoftwaretesting.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    static ConfigReader configReader = new ConfigReader();
    protected static final String adminEmail = configReader.getProperty("admin.email");
    protected static final String adminPassword = configReader.getProperty("admin.password");
    protected static final String defaultPassword = configReader.getProperty("default.password");

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

    public void loginUser(String email, String password) {
        loginPage.open()
                .isLoaded()
                .login(email, password);
        accountPage.isLoaded();
        homePage.open();
    }

    public String registerUser(String email) {
        return userSteps.registerUser(email, defaultPassword);
    }

    public void loginAsAdmin() {
        loginPage.open()
                .isLoaded()
                .login(adminEmail, adminPassword);
        accountPage.isLoaded();
    }

    public void deleteUser(String userId) {
        userSteps.deleteUser(userId);
    }

    public UserSearch searchUsers(String queryPhrase) {
        return userSteps.searchUsers(queryPhrase);
    }

    public String getLoginToken(String email, String password) {
        return userSteps.loginUser(email, password);
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
