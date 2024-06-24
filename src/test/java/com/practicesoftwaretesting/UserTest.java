package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.LoginResponse;
import com.practicesoftwaretesting.user.model.RegisterUserResponse;
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
                .as(RegisterUserResponse.class);
        assertNotNull(registerUserResponse.getId());

        // Login user
        var loginRequestBody = new LoginRequest(userEmail, DEFAULT_PASSWORD);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .as(LoginResponse.class);
        assertNotNull(userLoginResponse.getAccessToken());

        // Login as admin
        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .as(LoginResponse.class);

        // Delete user
        var userId = registerUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        userController.withToken(token).deleteUser(userId)
                .then()
                .statusCode(204);
    }

}
