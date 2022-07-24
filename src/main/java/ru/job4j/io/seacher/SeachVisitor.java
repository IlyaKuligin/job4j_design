package ru.job4j.io.seacher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SeachVisitor extends SimpleFileVisitor<Path> {

    StringBuilder fileResult = new StringBuilder();
    private Predicate<Path> condition;

    public SeachVisitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    public String getFile() {
        return fileResult.toString();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            fileResult.append(file.toString());
            fileResult.append(System.lineSeparator());
        }
        return CONTINUE;
    }
}
