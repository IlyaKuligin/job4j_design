package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean availability;
    @XmlAttribute
    private String color;
    @XmlAttribute
    private int speed;
    private Transmission transmission;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Car() {
    }

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
