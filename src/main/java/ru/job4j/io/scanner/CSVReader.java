package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.get("path"), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            try (var scanner = new Scanner(in).useDelimiter(argsName.get("delimiter"))) {
                String[] filtersElements = argsName.get("filter").split(",");
                String[] nameElements = scanner.nextLine().split(";");
                List<Integer> indexes = new ArrayList<>();
                for (int i = 0; i < nameElements.length; i++) {
                    for (String el : filtersElements) {
                        if (el.equals(nameElements[i])) {
                            if (i == 0) {
                                out.print(nameElements[i]);
                            } else {
                                out.print(";" + nameElements[i]);
                            }
                            indexes.add(i);
                        }
                    }
                }
                out.print(System.lineSeparator());
                while (scanner.hasNextLine()) {
                    nameElements = scanner.nextLine().split(";");
                    for (Integer index : indexes) {
                        if (index == indexes.get(0)) {
                            out.print(nameElements[index]);
                        } else {
                            out.print(";" + nameElements[index]);
                        }
                    }
                    out.print(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateParameters(String[] args) {
        boolean rsl = false;
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        rsl = true;
        return rsl;
    }

    public static void main(String[] args) throws Exception {
        if (validateParameters(args)) {
            CSVReader.handle(ArgsName.of(args));
        }
    }
}
