package testcomplect;


import io.restassured.response.Response;
import modelclasses.Courier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.CourierSteps;
import utils.CourierGenerator;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourierLoginTest {
    private Courier courier;
    private CourierSteps courierSteps;
    private int courierId;

    @BeforeEach
    public void setUp() {
        courierSteps = new CourierSteps();
        courier = CourierGenerator.getRandomCourier();
        courierSteps.createCourier(courier);
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    public void courierCanLogin() {
        Response response = courierSteps.loginCourier(courier);
        courierId = response.jsonPath().getInt("id");
        response.then().statusCode(200);
        response.then().body("id", notNullValue());
    }

    @Test
    public void loginWithoutLoginReturns400() {
        Courier courierWithoutLogin = new Courier(null, "1234", "Test");
        Response response = courierSteps.loginCourier(courierWithoutLogin);
        assertEquals(400, response.statusCode());
        assertEquals("Недостаточно данных для входа", response.jsonPath().getString("message"));
    }

    @Test
    public void loginWithoutPasswordReturns400() {
        Courier courierWithoutPassword = new Courier(courier.getLogin(), "", "Test");
        Response response = courierSteps.loginCourier(courierWithoutPassword);
        assertEquals(400, response.statusCode());
        assertEquals("Недостаточно данных для входа", response.jsonPath().getString("message"));
    }

    @Test
    public void loginWithWrongLoginReturns404() {
        Courier wrongCourier = new Courier("wrongLogin", "1234", "Test");
        Response response = courierSteps.loginCourier(wrongCourier);
        assertEquals(404, response.statusCode());
    }

    @Test
    public void loginWithWrongPasswordReturns404() {
        Courier wrongCourier = new Courier(courier.getLogin(), "wrongPassword", "Test");
        Response response = courierSteps.loginCourier(wrongCourier);
        assertEquals(404, response.statusCode());
    }

    @Test
    public void loginNonExistentCourierReturns404() {
        Courier wrongCourier = new Courier("ghost", "1234", "Ghost");
        Response response = courierSteps.loginCourier(wrongCourier);
        assertEquals(404, response.statusCode());
    }

    @AfterEach
    public void tearDown() {

        if (courierId != 0) {
            courierSteps.deleteCourier(courierId);
        }
    }
}
