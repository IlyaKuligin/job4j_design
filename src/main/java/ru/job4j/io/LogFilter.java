package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LogFilter {

    public static Optional<List<String>> filter(String file) {
        Optional<List<String>> rsl = Optional.empty();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = Optional.of(in.lines()
                    .filter(el -> {
                        String[] str = el.split(" ");
                        return str[str.length - 2].equals("404");
                    })
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(Optional<List<String>> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.ifPresent(strings -> strings.forEach(out::println));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Optional<List<String>> log = filter("log.txt");
        /*log.ifPresent(strings -> strings.forEach(System.out::println));*/
        save(log, "404.txt");
    }
}
