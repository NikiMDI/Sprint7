package testcomplect;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import modelclasses.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import steps.OrderSteps;

import java.util.Locale;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.notNullValue;

public class OrderCreateTest {

    private final OrderSteps orderSteps = new OrderSteps();
    private final Faker faker = new Faker(new Locale("ru"));

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
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().streetAddress(),
                faker.number().numberBetween(1, 100),
                faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(1, 10),
                "2026-06-06",
                faker.lorem().sentence(),
                colors
        );

        Response response = orderSteps.createOrder(order);

        response.then().statusCode(201);
        response.then().body("track", notNullValue());
    }
}