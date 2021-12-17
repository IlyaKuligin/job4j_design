package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        int[][] multi = multiple(10);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < multi.length; i++) {
                for (int j = 0; j < multi.length; j++) {
                    if (multi[i][j] < 10) {
                        out.write(("  " + multi[i][j]).getBytes());
                    } else {
                        out.write((" " + multi[i][j]).getBytes());
                    }
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] multiple(int size) {
        int[][] multiple = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                multiple[i][j] = (i + 1) * (j + 1);
            }
        }
        return multiple;
    }
}
