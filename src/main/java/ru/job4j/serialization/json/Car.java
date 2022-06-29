package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean availability;
    private final String color;
    private final int speed;
    private final Transmission transmission;
    private final String[] statuses;

    public Car(boolean availability, String color, int speed, Transmission transmission, String[] statuses) {
        this.availability = availability;
        this.color = color;
        this.speed = speed;
        this.transmission = transmission;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Car{"
                + "availability=" + availability
                + ", color='" + color + '\''
                + ", speed=" + speed
                + ", transmission=" + transmission
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
