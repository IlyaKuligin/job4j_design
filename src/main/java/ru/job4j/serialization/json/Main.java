package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(true, "White", 180, new Transmission("automatic", 5),
                new String[] {"alarm system", "full equipment"});

        /* Преобразуем объект Car в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /* Модифицируем json-строку */
        final String carJson =
                "{"
                       + "\"availability\":false,"
                       + "\"color\":\"White\","
                       + "\"speed\":170,"
                       + "\"transmission\":"
                       + "{"
                       + "\"type\":\"mechanical\","
                       + "\"numberGears\":5"
                       + "},"
                       + "\"statuses\":"
                       + "[\"alarm system\",\"full equipment\"]"
                       + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
