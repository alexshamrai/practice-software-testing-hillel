package com.practicesoftwaretesting;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;

public class UserTest {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.practicesoftwaretesting.com")
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void testBrands() {
        RestAssured.given()
                .get("/brands")
                .then()
                .statusCode(200);
    }

    @Test
    void testUser() {
        // Register user
        var registerUserRequest = """
                {
                  "first_name": "John",
                  "last_name": "Lennon",
                  "address": "Street 1",
                  "city": "City",
                  "state": "State",
                  "country": "Country",
                  "postcode": "1234AA",
                  "phone": "0987654321",
                  "dob": "1941-01-01",
                  "password": "12Example#",
                  "email": "john@lennon.example"
                }
                """;
        var registerUserResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registerUserRequest)
                .post("/users/register")
                .as(RegisterUserResponse.class);

        // Login user
        var loginRequestBody = """
                {
                  "email": "john@lennon.example",
                  "password": "12Example#"
                }
                """;

        var userLoginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequestBody)
                .post("/users/login")
                .as(LoginResponse.class);

        // Login as admin
        var adminLoginRequestBody = """
                {
                  "email": "admin@practicesoftwaretesting.com",
                  "password": "welcome01"
                }
                                """;
        var adminloginResponse = RestAssured.given()
                .contentType(JSON)
                .body(adminLoginRequestBody)
                .post("/users/login")
                .as(LoginResponse.class);

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
}
