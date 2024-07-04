package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.user.model.*;

public class UserController extends BaseController<UserController> {

    public ResponseDecorator<RegisterUserResponse> registerUser(RegisterUserRequest registerUserRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(registerUserRequest)
                        .post("/users/register"),
                RegisterUserResponse.class
        );
    }

    public ResponseDecorator<LoginResponse> loginUser(LoginRequest loginRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(loginRequest)
                        .post("/users/login")
                , LoginResponse.class
        );
    }

    public ResponseDecorator<Void> deleteUser(String userId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("users/" + userId),
                Void.class
        );
    }

    public ResponseDecorator<UserSearch> searchUsers(String queryPhrase) {
        return new ResponseDecorator<>(
                baseClient()
                        .get("/users/search?q=" + queryPhrase),
                UserSearch.class
        );
    }
}
