package testcomplect;

import io.restassured.response.Response;
import modelclasses.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import steps.OrderSteps;
import static org.hamcrest.Matchers.notNullValue;
import java.util.stream.Stream;

public class OrderCreateTest {

    private final OrderSteps orderSteps = new OrderSteps();

    public static Stream<Arguments> colorData() {

        return Stream.of(
                Arguments.of((Object) new String[]{"BLACK"}),
                Arguments.of((Object) new String[]{"GREY"}),
                Arguments.of((Object) new String[]{"BLACK", "GREY"}),
                Arguments.of((Object) new String[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("colorData")
    public void orderCanBeCreatedWithDifferentColors(String[] colors) {

        Order order = new Order(
                "Nikita",
                "Test",
                "Пушкина 3",
                1,
                "+79999999999",
                5,
                "2026-06-06",
                "Test order",
                colors
        );

        Response response = orderSteps.createOrder(order);

        response.then().statusCode(201);
        response.then().body("track", notNullValue());
    }
}