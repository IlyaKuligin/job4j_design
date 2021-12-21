package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Значение ключа " + key + " не найдено");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            if (!s.startsWith("-") || !s.contains("=") || (s.indexOf('=') != s.lastIndexOf('='))) {
                throw new IllegalArgumentException("Некорректно введены аргументы");
            }
            s = s.substring(1);
            String[] parametrs = s.split("=");
            if (parametrs.length != 2) {
                throw new IllegalArgumentException("Некорректно введены аргументы");
            }
            values.put(parametrs[0], parametrs[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
