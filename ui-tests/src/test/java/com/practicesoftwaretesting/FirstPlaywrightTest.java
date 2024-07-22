package com.practicesoftwaretesting;

import com.microsoft.playwright.*;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstPlaywrightTest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(100));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void registerNewUserAndLogin() {
        page.navigate("https://practicesoftwaretesting.com/");
        assertThat(page.locator(".img-fluid")).isVisible();

        // Click on the Sign In button
        page.locator("[data-test=nav-sign-in]").click();
        assertThat(page.locator("h3")).hasText("Login");

        // Click on the Register link
        page.locator("[data-test=register-link]").click();

        // Register page is opened
        assertThat(page.locator("h3")).hasText("Customer registration");

        var user = getUser();
        page.locator("#first_name").fill(user.getFirstName());
        page.locator("#last_name").fill(user.getLastName());
        page.locator("#dob").fill(user.getDob());
        page.locator("#address").fill(user.getAddress());
        page.locator("#city").fill(user.getCity());
        page.locator("#state").fill(user.getState());
        page.locator("#country").selectOption(user.getCountry());
        page.locator("#postcode").fill(user.getPostcode());
        page.locator("#phone").fill(user.getPhone());
        page.locator("#email").fill(user.getEmail());
        page.locator("#password").fill(user.getPassword());
        page.locator("[data-test=register-submit]").click();

        // Login page is opened
        assertThat(page.locator("h3")).hasText("Login");
        page.locator("#email").fill(user.getEmail());
        page.locator("#password").fill(user.getPassword());
        page.locator("[data-test=login-submit]").click();

        // Account page is opened
        assertThat(page.locator("[data-test=page-title]")).hasText("My account");
        String fullUserName = user.getFirstName() + " " + user.getLastName();
        assertThat(page.locator("[data-test=nav-menu]")).hasText(fullUserName);
    }

    private RegisterUserRequest getUser() {
        return RegisterUserRequest.builder()
                .firstName("George")
                .lastName("Harrison")
                .address("1243 Some Street")
                .city("Liverpool")
                .country("Uzbekistan")
                .state("Merseyside")
                .postcode("1234")
                .phone("123456677")
                .dob("1946-01-01")
                .email("georgeharrison1234@gmail.com")
                .password("12Example#")
                .build();
    }

}
