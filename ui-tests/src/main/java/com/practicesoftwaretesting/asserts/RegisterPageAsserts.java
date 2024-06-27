package com.practicesoftwaretesting.asserts;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.utils.SelectorUtils.byFor;

public class RegisterPageAsserts {

    public RegisterPageAsserts hasCorrectInfo() {
        $(byFor("first_name")).shouldHave(text("First name"));
        $(byId("first_name")).shouldHave(attribute("placeholder", "First name *"));

        $(byFor("last_name")).shouldHave(text("Last name"));
        $(byId("last_name")).shouldHave(attribute("placeholder", "Your last name *"));

        $(byFor("dob")).shouldHave(text("Date of birth *"));
        $(byId("dob")).shouldHave(attribute("type", "date"));

        $(byFor("address")).shouldHave(text("Address"));
        $(byId("address")).shouldHave(attribute("placeholder", "Your Address *"));

        $(byFor("postcode")).shouldHave(text("Postcode"));
        $(byId("postcode")).shouldHave(attribute("placeholder", "Your Postcode *"));

        $(byFor("city")).shouldHave(text("City"));
        $(byId("city")).shouldHave(attribute("placeholder", "Your City *"));

        $(byFor("state")).shouldHave(text("State"));
        $(byId("state")).shouldHave(attribute("placeholder", "Your State *"));

        $(byFor("country")).shouldHave(text("Country"));
        $(byId("country")).shouldHave(tagName("select"));
        $(byId("country")).findAll("option")
                .filter(text("Your country *"))
                .shouldHave(size(1))
                .get(0)
                .shouldBe(selected);

        $(byFor("email")).shouldHave(text("Email address"));
        $(byId("email")).shouldHave(attribute("placeholder", "Your email *"));

        $(byFor("password")).shouldHave(text("Password"));
        $(byId("password")).shouldHave(attribute("placeholder", "Your password *"));

        return this;
    }
}
