package com.practicesoftwaretesting.user;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import com.practicesoftwaretesting.user.model.UserSearch;
import com.practicesoftwaretesting.utils.ConfigReader;

public class UserSteps {

    ConfigReader configReader = new ConfigReader();
    String defaultPassword = configReader.getProperty("default.password");
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");

    public String registerUser(String userEmail, String password) {
        var userController = new UserController();
        var registerUserRequest = buildUser(userEmail, password);
        return userController.registerUser(registerUserRequest)
                .as()
                .getId();
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

    public void deleteUser(String userId) {
        var token = loginUser(adminEmail, adminPassword);
        new UserController().withToken(token).deleteUser(userId)
                .assertStatusCode(204);
    }

    public UserSearch searchUsers(String queryPhrase) {
        var token = loginUser(adminEmail, adminPassword);
        return new UserController().withToken(token).searchUsers(queryPhrase)
                .as();
    }

    public static RegisterUserRequest buildUser(String email, String password) {
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

    public static String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}
