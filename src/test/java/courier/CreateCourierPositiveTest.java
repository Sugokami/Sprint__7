package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierPositiveTest {
    private int courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Create new courier valid data")
    @Description("Basic test for /courier endpoint")
    public void testCreateNewCourierAndStatus201AndPrintResponseBody() {
        Profile profile = new Profile("Stesha15", "123456", "Zelimkhan");
        Response response = given().log().all().contentType(ContentType.JSON).body(profile).when().post("/api/v1/courier");
        response.then().statusCode(201).assertThat().body("ok",equalTo(true));
        System.out.println(response.body().asString());
    }

    @After
    public void deleteCourier() {
        Login login1 = new Login("Stesha15", "123456");
        courierId = given().log().all().contentType(ContentType.JSON).body(login1).when().post("/api/v1/courier/login").then().extract().path("id");
        String json = String.format("{\"id\": \"%d\"}", courierId);
        given().log().all().contentType(ContentType.JSON).body(json).when().delete("/api/v1/courier" +"/"+ courierId);
    }
}
