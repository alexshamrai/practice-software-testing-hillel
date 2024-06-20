package com.practicesoftwaretesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.LoginResponse;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import com.practicesoftwaretesting.user.model.RegisterUserResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    private static final String USER_PASSWORD = "12Example#";
    private String userEmail;

    UserController userController = new UserController();

    static {
        configureRestAssured();
        RestAssured.requestSpecification = new RequestSpecBuilder()
//                .setBaseUri("https://api.practicesoftwaretesting.com")
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    private static void configureRestAssured() {
        var objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(
                        RestAssured.config()
                                .getObjectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> objectMapper)
                );
    }

    @Test
    void testUser() {
        userEmail = getUserEmail();
        // Register user
        var registerUserRequest = buildUser();
        var registerUserResponse = userController.registerUser(registerUserRequest)
                .as(RegisterUserResponse.class);
        assertNotNull(registerUserResponse.getId());

        // Login user
        var loginRequestBody = new LoginRequest(userEmail, USER_PASSWORD);
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
        userController.deleteUser(userId, token)
                .then()
                .statusCode(204);
    }

    private RegisterUserRequest buildUser() {
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
                .password(USER_PASSWORD)
                .email(userEmail)
                .build();
    }

    private String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}
