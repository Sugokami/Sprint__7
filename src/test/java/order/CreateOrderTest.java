package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String[] color;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] params() {
        return new Object[][]{
                {new String[] {"GREY", "BLACK"}},
                {new String[] {"GREY"}},
                {new String[] {"BLACK"}},
                {new String[] {""}},
        };
    }

    @Test
    @DisplayName("Create new order with different colors of scooter")
    @Description("Basic test for /order endpoint")
    public void testCreateNewOrderStatus201AndResponseBody() {
        Order order2 = new Order("Whinnie", "The Pooh", "Under the tree", 4, "+7-905-123-45-67", 1, "2022-12-12", "", color);
        Response response = given().log().all().contentType(ContentType.JSON).body(order2).when().post("/api/v1/orders");
        response.then().statusCode(201).assertThat().body(containsString("track"));
        System.out.println(response.body().asString());
    }

}
