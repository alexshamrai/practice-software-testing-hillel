package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserController {

    private static final String BASE_URI = "https://api.practicesoftwaretesting.com";

    public Response registerUser(RegisterUserRequest registerUserRequest) {
        return baseClient()
                .body(registerUserRequest)
                .post("/users/register");
    }

    public Response loginUser(LoginRequest loginRequest) {
        return baseClient()
                .body(loginRequest)
                .post("/users/login");
    }

    public Response deleteUser(String userId, String token) {
        return baseClient()
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId);
    }

    private RequestSpecification baseClient() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }
}
