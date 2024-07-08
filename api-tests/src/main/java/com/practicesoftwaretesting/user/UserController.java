package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.user.model.*;
import io.qameta.allure.Step;

public class UserController extends BaseController<UserController> {

    @Step("Register user")
    public ResponseDecorator<RegisterUserResponse> registerUser(RegisterUserRequest registerUserRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(registerUserRequest)
                        .post("/users/register"),
                RegisterUserResponse.class
        );
    }

    @Step("Login user")
    public ResponseDecorator<LoginResponse> loginUser(LoginRequest loginRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(loginRequest)
                        .post("/users/login")
                , LoginResponse.class
        );
    }

    @Step("Delete user")
    public ResponseDecorator<Void> deleteUser(String userId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("users/" + userId),
                Void.class
        );
    }

    @Step("Search users")
    public ResponseDecorator<UserSearch> searchUsers(String queryPhrase) {
        return new ResponseDecorator<>(
                baseClient()
                        .get("/users/search?q=" + queryPhrase),
                UserSearch.class
        );
    }
}
