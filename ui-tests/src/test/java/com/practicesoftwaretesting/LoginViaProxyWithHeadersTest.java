package com.practicesoftwaretesting;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.practicesoftwaretesting.user.UserSteps.getUserEmail;

public class LoginViaProxyWithHeadersTest extends BaseTest {

    String userId;
    String token;

    @BeforeEach
    void setup() {
        var email = getUserEmail();
        userId = registerUser(email);
        token = getLoginToken(email, defaultPassword);
        open("/");
    }

    @Test
    void loginViaProxyWithHeaders() {
        WebDriverRunner.getSelenideProxy()
                .getProxy()
                .addHeader("Authorization", "Bearer " + token);
        open("/contact");
        Selenide.sleep(10000);
    }

    @AfterEach
    void cleanup() {
        deleteUser(userId);
    }
}
