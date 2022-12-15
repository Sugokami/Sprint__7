package org.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.Client;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderClient extends Client {
    public static final String ROOT = "/orders";

    @Step("Create an order")
    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Order created successfully")
    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat().body(containsString("track"));
    }

    @Step("Send get request")
    public Response sendGetRequest() {
        Response response = spec().get(ROOT);
        return response;
    }

    @Step("Assert that response is not nullable")
    public void compareResponseIsNotNull(Response response) {
        response.then().assertThat().body(notNullValue());
    }
}
