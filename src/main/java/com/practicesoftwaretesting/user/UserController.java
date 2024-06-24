package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import io.restassured.response.Response;

public class UserController extends BaseController<UserController> {

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

    public Response deleteUser(String userId) {
        return baseClient()
                .delete("users/" + userId);
    }
}
