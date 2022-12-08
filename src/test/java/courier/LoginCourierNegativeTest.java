package courier;

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
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class LoginCourierNegativeTest {
    @Parameterized.Parameter()
    public String login;

    @Parameterized.Parameter(1)
    public String password;

    @Parameterized.Parameter(2)
    public int statusCode;

    @Parameterized.Parameter(3)
    public String message;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Parameterized.Parameters(name = "login: {0}, password: {1}, firstName: {2}, statusCode: {3}, message: {4}")
    public static Object[][] params() {
        return new Object[][]{
                {"Stesha1", "", 400, "Недостаточно данных для входа"},
                {"", "12345", 400, "Недостаточно данных для входа"},
                {"Stesha456454", "12345", 404, "Учетная запись не найдена"},
        };
    }

    @Test
    @DisplayName("Login courier without 1 element and not existing name")
    @Description("Negative test for login courier")
    public void testCheckUserNameAndPrintResponseBody() {

        Login login1 = new Login(login, password);
        Response response = given().contentType(ContentType.JSON).body(login1).when().post("/api/v1/courier/login");
        response.then().statusCode(statusCode).assertThat().body("message",equalTo(message));
        System.out.println(response.body().asString());

    }
}
