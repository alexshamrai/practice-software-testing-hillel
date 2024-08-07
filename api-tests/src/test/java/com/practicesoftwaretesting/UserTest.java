package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.user.assertions.LoginResponseAsserts;
import com.practicesoftwaretesting.user.assertions.RegisterUserResponseAsserts;
import com.practicesoftwaretesting.user.model.LoginRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.practicesoftwaretesting.user.UserSteps.getUserEmail;
import static com.practicesoftwaretesting.user.UserSteps.buildUser;

public class UserTest extends BaseTest {

    private String userEmail;
    private String userId;

    UserController userController = new UserController();

    @Test
    @Tag("Smoke")
    void testUser() {
        userEmail = getUserEmail();
        var expectedUser = buildUser(userEmail, defaultPassword);
        var registerUserResponse = userController.registerUser(expectedUser)
                .assertStatusCode(201)
                .as();
        new RegisterUserResponseAsserts(registerUserResponse)
                .createdAtIsNotNull()
                .firstNameIs(expectedUser.getFirstName())
                .lastNameIs(expectedUser.getLastName())
                .countryIs(expectedUser.getCountry())
                .phoneIs(expectedUser.getPhone())
                .cityIs(expectedUser.getCity())
                .addressIs(expectedUser.getAddress());

        var loginRequestBody = new LoginRequest(userEmail, defaultPassword);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .assertStatusCode(200)
                .as();
        new LoginResponseAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");
        userId = registerUserResponse.getId();
    }

    @AfterEach
    void deleteUser() {
        var token = loginAsAdmin();
        userController.withToken(token).deleteUser(userId)
                .assertStatusCode(204);
    }

}
