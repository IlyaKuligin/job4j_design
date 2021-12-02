package ru.job4j;

import ru.job4j.map.User;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        User ingenier = new User("Ivan", 2,
                new GregorianCalendar(1986, 2, 25));
        User programmer = new User("Ivan", 2,
                new GregorianCalendar(1986, 2, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(ingenier, new Object());
        map.put(programmer, new Object());
        System.out.println(map);
    }
}
