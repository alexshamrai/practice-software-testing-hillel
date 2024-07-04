package com.practicesoftwaretesting;

import com.practicesoftwaretesting.pages.*;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserTest extends BaseTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    AccountPage accountPage = new AccountPage();

    @Test
    void registerNewUserAndLogin() {
        homePage.open()
                .isLoaded();
        header.clickSignInMenuItem();
        loginPage.isLoaded()
                .clickRegisterYourAccount();
        registerPage.isLoaded()
                .assertThat()
                .hasCorrectInfo();

        var user = getUser();
        registerPage.registerNewUser(user);

        loginPage.isLoaded()
                .login(user.getEmail(), user.getPassword());

        accountPage.isLoaded();
        header.assertThat().isSignedId(user.getFirstName() + " " + user.getLastName());
    }

    @AfterEach
    void cleanup() {
        var users = searchUsers("Harrison");
        users.getData().forEach(userToDelete -> deleteUser(userToDelete.getId()));
    }

    private RegisterUserRequest getUser() {
        return RegisterUserRequest.builder()
                .firstName("George")
                .lastName("Harrison")
                .address("1243 Some Street")
                .city("Liverpool")
                .country("Uzbekistan")
                .state("Merseyside")
                .postcode("1234")
                .phone("123456677")
                .dob("01/01/1946")
                .email("georgeharrison@gmail.com")
                .password("12Example#")
                .build();
    }
}
