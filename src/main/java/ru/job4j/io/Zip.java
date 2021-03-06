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

    private static Path validateParameters(ArgsName argsName) {
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
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Incorrectly entered arguments. Usage java -jar pack.jar -d=ROOT_FOLDER -e=FILE_FORMAT -o=END_FOLDER.zip");
        }
        List<File> fileList = new ArrayList<>();
        argsName = ArgsName.of(args);
        try {
            Search.search(
                    validateParameters(argsName),
                    p -> !p.toFile().getName().endsWith('.' + argsName.get("e"))
            ).forEach(e -> fileList.add(e.toFile()));
            packFiles(fileList, new File(argsName.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
