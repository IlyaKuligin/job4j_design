package ru.job4j.io.seacher;

import ru.job4j.io.ArgsName;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSeacher {

    public static void seach(String d, String n, String t, String o) throws Exception {
        Path start = Paths.get(d);
        SeachVisitor searcher;
        if ("name".equals(t)) {
            searcher = new SeachVisitor(p -> p.toFile().getName().equals(n));
        } else if ("mask".equals(t)) {
            searcher = new SeachVisitor(p -> p.toFile().getName().contains(n));
        } else {
            throw new IllegalArgumentException("Некорректно введен аргумент \"тип поиска\"");
        }
        Files.walkFileTree(start, searcher);
        String fileRsl = searcher.getFile();

        try (PrintWriter outfile =
                     new PrintWriter(new BufferedOutputStream(new FileOutputStream(o)))) {
            outfile.print(fileRsl);
        } catch (FileNotFoundException e) {
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
            ArgsName argsName = ArgsName.of(args);
            String d = argsName.get("d");
            String n = argsName.get("n");
            String t = argsName.get("t");
            String o = argsName.get("o");
            FileSeacher.seach(d, n, t, o);
        }
    }
}
