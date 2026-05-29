package apiclasses;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import modelclasses.Order;

import static io.restassured.RestAssured.given;

public class OrderApi extends BaseClient{

    private static final String CREATE_ORDER = "/api/v1/orders";

    private static final String GET_ORDERS = "/api/v1/orders";

    public Response createOrder(Order order) {

        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .body(order)
                .post(CREATE_ORDER);
    }

    public Response getOrders() {

        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .get(GET_ORDERS);
    }
}