package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.courier.CourierChecks;
import org.courier.CourierClient;
import org.courier.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class LoginCourierNegativeTest {

    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    @Parameterized.Parameter()
    public String login;

    @Parameterized.Parameter(1)
    public String password;

    @Parameterized.Parameter(2)
    public int statusCode;

    @Parameterized.Parameter(3)
    public String message;

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
    public void testCheckUserNameAndResponseBody() {
        Login creds = new Login(login, password);
        ValidatableResponse loginResponse = client.logIn(creds);
        var messageResponse= check.loggedInUnsuccessfully(loginResponse, statusCode);
        assertThat(messageResponse, containsString(message));
    }
}
