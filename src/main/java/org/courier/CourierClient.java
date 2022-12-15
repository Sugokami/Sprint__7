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
    public ValidatableResponse delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);

        return spec()
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();

    }
}
