package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        StringBuilder rsl = new StringBuilder();
        List<String> values = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            values = read.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < values.size(); i++) {
            if ((values.get(i).startsWith("500") || values.get(i).startsWith("400"))
                && (!values.get(i - 1).startsWith("500") && !values.get(i - 1).startsWith("400"))) {
                rsl.append(values.get(i).split(" ")[1]).append(";");
            }
            if ((!values.get(i).startsWith("500") && !values.get(i).startsWith("400"))
                    && (values.get(i - 1).startsWith("500") || values.get(i - 1).startsWith("400"))) {
                rsl.append(values.get(i).split(" ")[1]).append(";").append("\n");
            }
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server/server.log", "./data/server/unavailable.csv");
    }
}
