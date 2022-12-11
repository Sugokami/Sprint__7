package org.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.Client;

public class CourierClient extends Client {
    public static final String ROOT = "/courier";

    @Step("Create courier")
    public ValidatableResponse create(Profile profile) {
        return spec()
                .body(profile)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Login in service")
    public ValidatableResponse logIn(Login creds) {
        return spec()
                .body(creds)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    @Step("Delete courier")
    public void delete(int courierId) {

    }
}
