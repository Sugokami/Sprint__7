package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.order.OrderClient;

public class OrderListTest {

    private final OrderClient client = new OrderClient();

    @Test
    @DisplayName("Show an orderlist")
    @Description("Basic test for orders")
    public void testGetOrderList() {
        Response response = client.sendGetRequest();
        client.compareResponseIsNotNull(response);

    }
}
