package ru.job4j.serialization.json;

public class Transmission {
    private final String type;
    private final int numberGears;

    public Transmission(String type, int numberGears) {
        this.type = type;
        this.numberGears = numberGears;
    }

    @Override
    public String toString() {
        return "Transmission{"
                + "type='" + type + '\''
                + ", numberGears=" + numberGears
                + '}';
    }
}
