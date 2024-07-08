package com.practicesoftwaretesting.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.practicesoftwaretesting.utils.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public abstract class BaseController<T> {

    private final ConfigReader configReader = new ConfigReader();

    private String authToken;

    static {
        configureRestAssured();
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

    protected RequestSpecification baseClient() {
        var requestSpecification = RestAssured.given()
                .baseUri(configReader.getProperty("base.api.url"))
                .filters(List.of(new LogRequestFilter(), new AllureRestAssured()))
                .contentType(ContentType.JSON);
        if (authToken != null) {
            requestSpecification.header("Authorization", "Bearer" + authToken);
        }
        return requestSpecification;
    }

    public T withToken(String token) {
        this.authToken = token;
        return (T) this;
    }

    public void cleanToken() {
        this.authToken = null;
    }
}
