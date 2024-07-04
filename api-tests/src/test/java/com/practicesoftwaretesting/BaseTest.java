package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.UserSteps;
import com.practicesoftwaretesting.utils.ConfigReader;

public abstract class BaseTest {

    ConfigReader configReader = new ConfigReader();
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");
    String defaultPassword = configReader.getProperty("default.password");

    UserSteps userSteps = new UserSteps();

    public String registerUser(String userEmail, String password) {
        return userSteps.registerUser(userEmail, password);
    }

    public String loginUser(String userEmail, String password) {
        return userSteps.loginUser(userEmail, password);
    }

    public String loginAsAdmin() {
        return loginUser(adminEmail, adminPassword);
    }
}
