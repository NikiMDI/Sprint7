package steps;

import apiclasses.OrderApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import modelclasses.Order;


public class OrderSteps {
    private final OrderApi orderApi =
            new OrderApi();

    @Step("Создание заказа")
    public Response createOrder(Order order) {

        return orderApi.createOrder(order);
    }

    @Step("Получение списка заказов")
    public Response getOrders() {

        return orderApi.getOrders();
    }
}
