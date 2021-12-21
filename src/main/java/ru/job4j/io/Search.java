package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        search(validateParameters(args), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static Path validateParameters(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Root folder or file format is null. Usage java -jar dir.jar ROOT_FOLDER FILE_FORMAT.");
        }
        Path start = Paths.get(args[0]);
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toAbsolutePath()));
        }
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("Not directory %s", start.toAbsolutePath()));
        }
        return start;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
    /*
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new PrintFiles());
    }
     */
}
