package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.get("path"), StandardCharsets.UTF_8))) {
            StringBuilder out = new StringBuilder();
            try (var scanner = new Scanner(in).useDelimiter(argsName.get("delimiter"))) {
                String[] filtersElements = argsName.get("filter").split(",");
                String[] nameElements = scanner.nextLine().split(";");
                List<Integer> indexes = new ArrayList<>();
                for (int i = 0; i < nameElements.length; i++) {
                    for (String el : filtersElements) {
                        if (el.equals(nameElements[i])) {
                            if (i == 0) {
                                out.append(nameElements[i]);
                            } else {
                                out.append(";" + nameElements[i]);
                            }
                            indexes.add(i);
                        }
                    }
                }
                out.append(System.lineSeparator());
                while (scanner.hasNextLine()) {
                    nameElements = scanner.nextLine().split(";");
                    for (Integer index : indexes) {
                        if (Objects.equals(index, indexes.get(0))) {
                            out.append(nameElements[index]);
                        } else {
                            out.append(";" + nameElements[index]);
                        }
                    }
                    out.append(System.lineSeparator());
                }
            }
            if (argsName.get("out").equals("stdout")) {
                System.out.print(out.toString());
            } else {
                try (PrintWriter outfile =
                             new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
                    outfile.print(out);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateParameters(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        for (String s : args) {
            if (!s.startsWith("-") || !s.contains("=") || (s.indexOf('=') != s.lastIndexOf('='))) {
                throw new IllegalArgumentException("Некорректно введены аргументы");
            }
            s = s.substring(1);
            String[] parametrs = s.split("=");
            if (parametrs.length != 2) {
                throw new IllegalArgumentException("Некорректно введены аргументы");
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        if (validateParameters(args)) {
            CSVReader.handle(ArgsName.of(args));
        }
    }
}
