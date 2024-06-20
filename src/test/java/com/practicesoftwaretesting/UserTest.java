package com.practicesoftwaretesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.javafaker.Faker;
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

    static {
        configureRestAssured();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.practicesoftwaretesting.com")
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
        var registerUserResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registerUserRequest)
                .post("/users/register")
                .as(RegisterUserResponse.class);
        assertNotNull(registerUserResponse.getId());

        // Login user
        var loginRequestBody = new LoginRequest(userEmail, USER_PASSWORD);
        var userLoginResponse = loginUser(loginRequestBody);
        assertNotNull(userLoginResponse.getAccessToken());

        // Login as admin
        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = loginUser(adminLoginRequestBody);

        // Delete user
        var userId = registerUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        RestAssured.given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId)
                .then()
                .statusCode(204);
    }

    private static LoginResponse loginUser(LoginRequest loginRequestBody) {
        return RestAssured.given()
                .contentType(JSON)
                .body(loginRequestBody)
                .post("/users/login")
                .as(LoginResponse.class);
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
