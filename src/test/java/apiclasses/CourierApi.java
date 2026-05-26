package apiclasses;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import modelclasses.Courier;

import static io.restassured.RestAssured.given;

public class CourierApi extends BaseClient {
    private static final String CREATE_COURIER = "/api/v1/courier";

    private static final String LOGIN_COURIER = "/api/v1/courier/login";

    private static final String DELETE_COURIER = "/api/v1/courier/";

    public Response createCourier(Courier courier) {

        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .body(courier)
                .post(CREATE_COURIER);
    }

    public Response loginCourier(Courier courier) {

        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .body(courier)
                .post(LOGIN_COURIER);
    }

    public Response deleteCourier(int courierId) {

        return given()
                .filter(new AllureRestAssured())
                .delete(DELETE_COURIER + courierId);
    }
}
