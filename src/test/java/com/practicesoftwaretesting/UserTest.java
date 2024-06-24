package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends BaseTest {

    private String userEmail;

    UserController userController = new UserController();

    @Test
    void testUser() {
        userEmail = getUserEmail();
        // Register user
        var registerUserRequest = buildUser(userEmail, DEFAULT_PASSWORD);
        var registerUserResponse = userController.registerUser(registerUserRequest)
                .assertStatusCode(201)
                .as();
        assertNotNull(registerUserResponse.getId());

        // Login user
        var loginRequestBody = new LoginRequest(userEmail, DEFAULT_PASSWORD);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .assertStatusCode(200)
                .as();
        assertNotNull(userLoginResponse.getAccessToken());

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
