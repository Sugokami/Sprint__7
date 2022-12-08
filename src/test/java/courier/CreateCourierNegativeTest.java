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
public class CreateCourierNegativeTest {
    @Parameterized.Parameter()
    public String login;

    @Parameterized.Parameter(1)
    public String password;

    @Parameterized.Parameter(2)
    public String firstName;

    @Parameterized.Parameter(3)
    public int statusCode;

    @Parameterized.Parameter(4)
    public String message;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Parameterized.Parameters(name = "login: {0}, password: {1}, firstName: {2}, statusCode: {3}, message: {4}")
    public static Object[][] params() {
        return new Object[][]{
                {"Stesha", "12345", "", 400, "Недостаточно данных для создания учетной записи"},
                {"Stesha1", "", "Steshanya", 400, "Недостаточно данных для создания учетной записи"},
                {"", "12345", "Steshanya", 400, "Недостаточно данных для создания учетной записи"},
                {"Stesha", "12345", "Steshanya", 409, "Этот логин уже используется. Попробуйте другой."},
                {"Stesha4", "4564564", "Pavel", 409, "Этот логин уже используется. Попробуйте другой."},
        };
    }

    @Test
    @DisplayName("Create courier without 1 required field and with same data")
    @Description("Negative test for creating courier")
    public void testCheckUserNameAndPrintResponseBody() {

        Profile profile = new Profile(login, password, firstName);
        Response response = given().log().all().contentType(ContentType.JSON).body(profile).when().post("/api/v1/courier");
        response.then().log().all().statusCode(statusCode).assertThat().body("message",equalTo(message));
        System.out.println(response.body().asString());
    }
}
