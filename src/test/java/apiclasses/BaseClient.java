package apiclasses;

import io.restassured.RestAssured;

public class BaseClient {
    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    public BaseClient() {
        RestAssured.baseURI = BASE_URL;
    }
}
