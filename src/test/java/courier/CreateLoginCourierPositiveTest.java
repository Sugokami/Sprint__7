package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.courier.CourierChecks;
import org.courier.CourierClient;
import org.courier.Login;
import org.courier.Profile;
import org.junit.After;
import org.junit.Test;


public class CreateLoginCourierPositiveTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    private int courierId;

    @Test
    @DisplayName("Create new courier valid data")
    @Description("Basic test for /courier endpoint")
    public void testCreateNewCourierAndLogin() {
        Profile profile = new Profile("Stesha15", "123456", "Zelimkhan");
        var response = client.create(profile);
        check.createdSuccessfully(response);
        Login creds = Login.from(profile);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
    }

    @After
    public void deleteCourier() {
        if (courierId > 0)
            client.delete(courierId);
    }
}
