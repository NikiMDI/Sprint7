package testcomplect;

import io.qameta.allure.Description;

import io.restassured.response.Response;
import modelclasses.Courier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.CourierSteps;
import utils.CourierGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourierCreateTest {
    private Courier courier;
    private CourierSteps courierSteps;
    private int courierId;

    @BeforeEach
    public void setUp() {
        courierSteps = new CourierSteps();
        courier = CourierGenerator.getRandomCourier();
    }

    @Test
    @DisplayName("Курьер может быть создан")
    @Description("Проверка успешного создания курьера")
    public void courierCanBeCreated() {
        Response response = courierSteps.createCourier(courier);
        assertEquals(201, response.statusCode());
        assertEquals(true, response.jsonPath().getBoolean("ok"));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void duplicateCourierCannotBeCreated() {
        Courier courier = CourierGenerator.getRandomCourier();
        courierSteps.createCourier(courier);
        Response response = courierSteps.createCourier(courier);
        assertEquals(409, response.statusCode());
        assertEquals("Этот логин уже используется. Попробуйте другой.", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Нельзя создать курьера без логина")
    public void courierWithoutLoginReturns400() {
        Courier courier = new Courier(null, "1234", "Test");
        Response response = courierSteps.createCourier(courier);
        assertEquals(400, response.statusCode());
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля")
    public void courierWithoutPasswordReturns400() {
        Courier courier = new Courier("ninja", null, "Test");
        Response response = courierSteps.createCourier(courier);
        assertEquals(400, response.statusCode());
    }

    @AfterEach
    public void tearDown() {
        if (courierId != 0) {
            courierSteps.deleteCourier(courierId);
        }
    }
}
