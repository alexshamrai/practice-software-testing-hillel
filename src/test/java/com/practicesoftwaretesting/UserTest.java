package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.user.assertions.LoginResponseAsserts;
import com.practicesoftwaretesting.user.assertions.RegisterUserResponseAsserts;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends BaseTest {

    private String userEmail;

    UserController userController = new UserController();

    @Test
    void testUser() {
        userEmail = getUserEmail();
        // Register user
        var expectedUser = buildUser(userEmail, DEFAULT_PASSWORD);
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

        // Login user
        var loginRequestBody = new LoginRequest(userEmail, DEFAULT_PASSWORD);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .assertStatusCode(200)
                .as();
        new LoginResponseAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");

        // Login as admin
        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .assertStatusCode(200)
                .as();

        // Delete user
        var userId = registerUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        userController.withToken(token).deleteUser(userId)
                .assertStatusCode(204);
    }

}
