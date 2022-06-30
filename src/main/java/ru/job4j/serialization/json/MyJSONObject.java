package ru.job4j.serialization.json;

import org.json.JSONObject;

public class MyJSONObject {
    public static void main(String[] args) {
        final Car car = new Car(true, "White", 180, new Transmission("automatic", 5),
                new String[] {"alarm system", "full equipment"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("availability", car.isAvailability());
        jsonObject.put("color", car.getColor());
        jsonObject.put("speed", car.getSpeed());
        jsonObject.put("transmission", car.getTransmission());
        jsonObject.put("statuses", car.getStatuses());

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(car).toString());
    }
}
