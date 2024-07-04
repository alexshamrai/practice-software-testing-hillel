package com.practicesoftwaretesting.user;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import com.practicesoftwaretesting.utils.ConfigReader;

public class UserSteps {

    ConfigReader configReader = new ConfigReader();
    String defaultPassword = configReader.getProperty("default.password");

    public void registerUser(String userEmail, String password) {
        var userController = new UserController();
        var registerUserRequest = buildUser(userEmail, password);
        userController.registerUser(registerUserRequest).as();
    }

    public String loginUser(String userEmail, String password) {
        var userController = new UserController();
        var userLoginResponse = userController.loginUser(new LoginRequest(userEmail, password))
                .as();
        return userLoginResponse.getAccessToken();
    }

    public String registerAndLoginNewUser() {
        var userEmail = getUserEmail();
        registerUser(userEmail, defaultPassword);
        return loginUser(userEmail, defaultPassword);
    }

    public RegisterUserRequest buildUser(String email, String password) {
        return RegisterUserRequest.builder()
                .firstName("John")
                .lastName("Lennon")
                .address("Street 1")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654321")
                .dob("1941-01-01")
                .password(password)
                .email(email)
                .build();
    }

    public String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}
