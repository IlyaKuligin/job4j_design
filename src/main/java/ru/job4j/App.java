package ru.job4j;

import ru.job4j.map.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        int x = 342;
        System.out.println(Integer.toBinaryString(x));
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(1986, 2, 25, 8, 20);
        calendar.set(Calendar.MILLISECOND, 10);
        User ingenier = new User("Ivan", 2, calendar);
        User programmer = new User("Ivan", 2, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(ingenier, new Object());
        map.put(programmer, new Object());
        System.out.println(map);
        System.out.println(ingenier.hashCode() == programmer.hashCode());
    }
}
