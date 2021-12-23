package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static ArgsName argsName = new ArgsName();

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Path validateParameters(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Root folder or file format is null. Usage java -jar dir.jar ROOT_FOLDER FILE_FORMAT.");
        }
        Path start = Paths.get(argsName.get("d"));
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toAbsolutePath()));
        }
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("Not directory %s", start.toAbsolutePath()));
        }
        return start;
    }

    public static void main(String[] args) {
        List<File> fileList = new ArrayList<>();
        argsName = ArgsName.of(args);
        try {
            Search.search(
                    validateParameters(args),
                    p -> !p.toFile().getName().endsWith('.' + argsName.get("e"))
            ).forEach(e -> fileList.add(e.toFile()));
            packFiles(fileList, new File(argsName.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
