package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transmission")
public class Transmission {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private int numberGears;

    public Transmission() {
    }

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
