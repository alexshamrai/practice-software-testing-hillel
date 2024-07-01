package com.practicesoftwaretesting.asserts;

import com.practicesoftwaretesting.pages.RegisterPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPageAsserts extends RegisterPage {

    public RegisterPageAsserts hasCorrectInfo() {
        $(FIRST_NAME_LABEL).shouldHave(text("First name"));
        $(FIRST_NAME_INPUT).shouldHave(attribute("placeholder", "First name *"));

        $(LAST_NAME_LABEL).shouldHave(text("Last name"));
        $(LAST_NAME_INPUT).shouldHave(attribute("placeholder", "Your last name *"));

        $(DOB_LABEL).shouldHave(text("Date of birth *"));
        $(DOB_INPUT).shouldHave(attribute("type", "date"));

        $(ADDRESS_LABEL).shouldHave(text("Address"));
        $(ADDRESS_INPUT).shouldHave(attribute("placeholder", "Your Address *"));

        $(POSTCODE_LABEL).shouldHave(text("Postcode"));
        $(POSTCODE_INPUT).shouldHave(attribute("placeholder", "Your Postcode *"));

        $(CITY_LABEL).shouldHave(text("City"));
        $(CITY_INPUT).shouldHave(attribute("placeholder", "Your City *"));

        $(STATE_LABEL).shouldHave(text("State"));
        $(STATE_INPUT).shouldHave(attribute("placeholder", "Your State *"));

        $(COUNTRY_LABEL).shouldHave(text("Country"));
        $(COUNTRY_INPUT).shouldHave(tagName("select"));
        $(COUNTRY_INPUT).findAll("option")
                .filter(text("Your country *"))
                .shouldHave(size(1))
                .get(0)
                .shouldBe(selected);

        $(PHONE_LABEL).shouldHave(text("Phone"));
        $(PHONE_INPUT).shouldHave(attribute("placeholder", "Your phone *"));

        $(EMAIL_LABEL).shouldHave(text("Email address"));
        $(EMAIL_INPUT).shouldHave(attribute("placeholder", "Your email *"));

        $(PASSWORD_LABEL).shouldHave(text("Password"));
        $(PASSWORD_INPUT).shouldHave(attribute("placeholder", "Your password *"));

        return this;
    }
}
