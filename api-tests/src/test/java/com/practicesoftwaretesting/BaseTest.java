package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.UserSteps;
import com.practicesoftwaretesting.utils.ConfigReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseTest {

    ConfigReader configReader = new ConfigReader();
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");
    String defaultPassword = configReader.getProperty("default.password");

    UserSteps userSteps = new UserSteps();

    public String registerUser(String userEmail, String password) {
        var token = userSteps.registerUser(userEmail, password);
        log.info("User " + userEmail + " is registered. Token: " + token);
        return token;
    }

    public String loginUser(String userEmail, String password) {
        return userSteps.loginUser(userEmail, password);
    }

    public String loginAsAdmin() {
        return loginUser(adminEmail, adminPassword);
    }
}
