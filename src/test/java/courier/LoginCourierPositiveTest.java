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
import static org.hamcrest.CoreMatchers.containsString;

public class LoginCourierPositiveTest {
    private int courierId;

    @Before
    public void setUpAndCreateCourier() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        Profile profile = new Profile("Stesha25", "123456", "Cindirella");
        given().log().all().contentType(ContentType.JSON).body(profile).when().post("/api/v1/courier");}

    @Test
    @DisplayName("Login existing courier")
    @Description("Basic test for login")
    public void testLoginCourierAndStatus200AndResponseBody() {
        Login login1 = new Login("Stesha25", "123456");
        Response response = given().log().all().contentType(ContentType.JSON).body(login1).when().post("/api/v1/courier/login");
        response.then().statusCode(200).assertThat().body(containsString("id"));
        courierId = response.then().extract().path("id");
        System.out.println(response.body().asString());
    }
    @After
    public void deleteCourier() {
        String json = String.format("{\"id\": \"%d\"}", courierId);
        given().log().all().contentType(ContentType.JSON).body(json).when().delete("/api/v1/courier" +"/"+ courierId);
    }
}
