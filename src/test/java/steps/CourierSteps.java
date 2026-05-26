package steps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import apiclasses.CourierApi;
import modelclasses.Courier;

public class CourierSteps {
    private final CourierApi courierApi = new CourierApi();

    @Step("Создание курьера")
    public Response createCourier(Courier courier) {
        return courierApi.createCourier(courier);
    }

    @Step("Логин курьера")
    public Response loginCourier(Courier courier) {
        return courierApi.loginCourier(courier);
    }

    @Step("Удаление курьера")
    public Response deleteCourier(int courierId) {
        return courierApi.deleteCourier(courierId);
    }
}
