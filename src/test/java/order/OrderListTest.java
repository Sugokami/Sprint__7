package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Show an orderlist")
    @Description("Basic test for orders")
    public void testGetOrderList() {
        Orders orders = given()
                .log().all().contentType(ContentType.JSON)
                .get("/api/v1/orders")
                .body().as(Orders.class);
        MatcherAssert.assertThat(orders, notNullValue());
    }
}
