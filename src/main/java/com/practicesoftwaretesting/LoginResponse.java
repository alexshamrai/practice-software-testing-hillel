package com.practicesoftwaretesting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    private String accessToken;
    private String tokenType;
    private int expiresIn;
}

