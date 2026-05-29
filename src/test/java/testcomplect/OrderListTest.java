package testcomplect;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import steps.OrderSteps;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    private final OrderSteps orderSteps =
            new OrderSteps();

    @Test
    public void ordersListContainsOrders() {

        Response response = orderSteps.getOrders();
        response.then().statusCode(200);
        response.then().body("orders", notNullValue());
    }
}