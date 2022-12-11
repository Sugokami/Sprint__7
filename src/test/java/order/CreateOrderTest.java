package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.order.Order;
import org.order.OrderClient;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final OrderClient client = new OrderClient();
    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] params() {
        return new Object[][]{
                {new String[]{"GREY", "BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{""}},
        };
    }

    @Test
    @DisplayName("Create new order with different colors of scooter")
    @Description("Basic test for /order endpoint")
    public void testCreateNewOrderStatus201AndResponseBody() {
        Order order2 = new Order("Whinnie", "The Pooh", "Under the tree", 4, "+7-905-123-45-67", 1, "2022-12-12", "", color);
        var response = client.create(order2);
        client.createdSuccessfully(response);
    }

}
