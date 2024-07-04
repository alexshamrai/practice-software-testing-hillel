package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.UserSteps;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import com.practicesoftwaretesting.utils.ConfigReader;

public abstract class BaseTest {

    ConfigReader configReader = new ConfigReader();
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");
    String defaultPassword = configReader.getProperty("default.password");

    UserSteps userSteps = new UserSteps();

    public void registerUser(String userEmail, String password) {
        userSteps.registerUser(userEmail, password);
    }

    public String loginUser(String userEmail, String password) {
        return userSteps.loginUser(userEmail, password);
    }

    public String loginAsAdmin() {
        return loginUser(adminEmail, adminPassword);
    }

    public String registerAndLoginNewUser() {
        return userSteps.registerAndLoginNewUser();
    }

    public RegisterUserRequest buildUser(String email, String password) {
        return userSteps.buildUser(email, password);
    }

    public String getUserEmail() {
        return userSteps.getUserEmail();
    }
}
