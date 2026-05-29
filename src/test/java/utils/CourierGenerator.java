package utils;

import modelclasses.Courier;

public class CourierGenerator {
    public static Courier getRandomCourier() {

        return new Courier(
                "ninja" + System.currentTimeMillis(),
                "1234",
                "Test"
        );
    }
}
