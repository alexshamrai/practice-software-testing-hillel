package com.practicesoftwaretesting;

import com.practicesoftwaretesting.pages.Header;
import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.pages.RegisterPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class UserTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();

    @Test
    void registerNewUserAndLogin() {
        open("https://practicesoftwaretesting.com/#/");
        homePage.isLoaded();
        header.clickSignInMenuItem();
        loginPage.isLoaded()
                .clickRegisterYourAccount();
        registerPage.isLoaded()
                .assertThat()
                .hasCorrectInfo();
    }
}
