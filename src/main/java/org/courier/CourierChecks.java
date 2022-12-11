package org.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourierChecks {
    @Step("Courier created successfully")
    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(201)
                .and()
                .body("ok", is(true));
    }

    @Step("Courier creation failed")
    public String creationFailed(ValidatableResponse response, int expectedStatusCode) {
        return response
                .assertThat()
                .statusCode(expectedStatusCode)
                .and()
                .body("message", not(blankOrNullString()))
                .extract()
                .path("message");
    }

    @Step("Successful login")
    public int loggedInSuccessfully(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }

    @Step("Login failed")
    public String loggedInUnsuccessfully(ValidatableResponse loginResponse, int expectedStatusCode) {
        return loginResponse
                .assertThat()
                .statusCode(expectedStatusCode)
                .and()
                .body("message", not(blankOrNullString()))
                .extract()
                .path("message");
    }
}
